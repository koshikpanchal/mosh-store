package com.koshikpanchal.store.test;

import org.springframework.stereotype.Service;

@Service("sms")
public class SMSNotificationService implements NotificationService {

    @Override
    public void send(String message, String recipientEmail) {
        System.out.println("Message send from SMS : " + message + " from " + recipientEmail);
    }
}
