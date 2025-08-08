package com.koshikpanchal.store.services;

import com.koshikpanchal.store.entity.Order;

public interface PaymentGateway {
    CheckoutSession createCheckoutSession(Order order);
}
