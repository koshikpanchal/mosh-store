package com.koshikpanchal.store.mosh;

//@Service
public class OrderServiceMosh {

    private PaymentService paymentService;

    public OrderServiceMosh(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public void placeOrder() {
        paymentService.processPayment(10);
    }

    public void setPaymentService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

}
