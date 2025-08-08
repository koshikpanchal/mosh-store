package com.koshikpanchal.store.controllers;

import com.koshikpanchal.store.dtos.CheckoutRequest;
import com.koshikpanchal.store.dtos.ErrorDto;
import com.koshikpanchal.store.exceptions.CartIsEmptyException;
import com.koshikpanchal.store.exceptions.CartNotFoundException;
import com.koshikpanchal.store.services.CheckoutService;
import com.stripe.exception.StripeException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/checkout")
@AllArgsConstructor
public class CheckoutController {

    private final CheckoutService checkoutService;

    @PostMapping
    public ResponseEntity<?> checkout(@Valid @RequestBody CheckoutRequest request) {
        try {
            return ResponseEntity.ok(checkoutService.checkout(request));

        } catch (StripeException ex) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorDto("error creating a checkout session"));
        }
    }

    @ExceptionHandler({CartNotFoundException.class, CartIsEmptyException.class})
    public ResponseEntity<ErrorDto> handleException(Exception ex) {
        return ResponseEntity.badRequest().body(new ErrorDto(ex.getMessage()));
    }

}
