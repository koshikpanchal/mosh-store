package com.koshikpanchal.store.test;

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
    public OrderService orderService() {
        if(paymentMethod.equals("stripe")){
            return new OrderService(stripe());
        }
        return new OrderService(paypal());
    }
}
