package com.koshikpanchal.store.exceptions;

public class CartNotFoundException extends RuntimeException {
    public CartNotFoundException() {
        super("cart is empty");
    }
}
