package com.koshikpanchal.store.services;

import com.koshikpanchal.store.dtos.OrderDto;
import com.koshikpanchal.store.exceptions.OrderNotFoundException;
import com.koshikpanchal.store.mapper.OrderMapper;
import com.koshikpanchal.store.repositories.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private AuthService authService;

    public List<OrderDto> getAllOrders() {
        var user = authService.getCurrentUser();
        var order = orderRepository.getOrdersByCustomer(user);
        return order.stream().map(orderMapper::toDto).toList();
    }

    public OrderDto getOrder(Long orderId) {
        var order = orderRepository.getOrderWithItems(orderId).orElseThrow(OrderNotFoundException::new);
        var user = authService.getCurrentUser();
        if (!order.isPlacedBy(user)) {
            throw new AccessDeniedException("You don't have access to this order");
        }
        return orderMapper.toDto(order);
    }
}
