package com.koshikpanchal.store.mosh;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Value("${payment.service:stripe}")
    private String paymentMethod;

    @Bean
    public PaymentService stripe() {
        return new StripePaymentService();
    }

    @Bean
    public PaymentService paypal() {
        return new PaypalPaymentService();
    }

    @Bean
    public OrderServiceMosh orderServiceMosh() {
        if (paymentMethod.equals("stripe")) {
            return new OrderServiceMosh(stripe());
        }
        return new OrderServiceMosh(paypal());
    }
}
