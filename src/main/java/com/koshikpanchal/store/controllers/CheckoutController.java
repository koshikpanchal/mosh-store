package com.koshikpanchal.store.controllers;

import com.koshikpanchal.store.dtos.CheckoutRequest;
import com.koshikpanchal.store.dtos.CheckoutResponse;
import com.koshikpanchal.store.dtos.ErrorDto;
import com.koshikpanchal.store.exceptions.CartIsEmptyException;
import com.koshikpanchal.store.exceptions.CartNotFoundException;
import com.koshikpanchal.store.exceptions.PaymentException;
import com.koshikpanchal.store.repositories.OrderRepository;
import com.koshikpanchal.store.services.CheckoutService;
import com.koshikpanchal.store.services.WebhookRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/checkout")
@RequiredArgsConstructor
public class CheckoutController {

    private final CheckoutService checkoutService;
    private final OrderRepository orderRepository;


    @PostMapping
    public CheckoutResponse checkout(@Valid @RequestBody CheckoutRequest request) {
        return checkoutService.checkout(request);
    }

    @PostMapping("/webhook")
    public void handleWebhook(
            @RequestHeader Map<String, String> headers,
            @RequestBody String payload
    ) {
        checkoutService.handleWebhookEvent(new WebhookRequest(headers, payload));
    }

    @ExceptionHandler({CartNotFoundException.class, CartIsEmptyException.class})
    public ResponseEntity<ErrorDto> handleException(Exception ex) {
        return ResponseEntity.badRequest().body(new ErrorDto(ex.getMessage()));
    }

    @ExceptionHandler(PaymentException.class)
    public ResponseEntity<?> handlePaymentException() {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorDto("error creating a checkout session"));
    }

}
