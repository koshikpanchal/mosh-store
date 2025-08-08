package com.koshikpanchal.store.services;

import com.koshikpanchal.store.dtos.CheckoutRequest;
import com.koshikpanchal.store.dtos.CheckoutResponse;
import com.koshikpanchal.store.entity.Order;
import com.koshikpanchal.store.exceptions.CartIsEmptyException;
import com.koshikpanchal.store.exceptions.CartNotFoundException;
import com.koshikpanchal.store.exceptions.PaymentException;
import com.koshikpanchal.store.repositories.CartRepository;
import com.koshikpanchal.store.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CheckoutService {

    private final CartRepository cartRepository;
    private final AuthService authService;
    private final OrderRepository orderRepository;
    private final CartService cartService;
    private final PaymentGateway paymentGateway;


    @Transactional
    public CheckoutResponse checkout(CheckoutRequest request) {
        var cart = cartRepository.getCartWithItems(request.getCartId()).orElse(null);
        if (cart == null) {
            throw new CartNotFoundException();
        }

        if (cart.isEmpty()) {
            throw new CartIsEmptyException();
        }

        var order = Order.fromCart(cart, authService.getCurrentUser());
        orderRepository.save(order);

        try {
            var session = paymentGateway.createCheckoutSession(order);

            cartService.clearCart(cart.getId());
            
            return new CheckoutResponse(order.getId(), session.getCheckoutUrl());
        } catch (PaymentException ex) {
            orderRepository.delete(order);
            throw ex;
        }
    }
}
