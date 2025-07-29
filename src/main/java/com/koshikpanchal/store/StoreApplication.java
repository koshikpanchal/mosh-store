package com.koshikpanchal.store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class StoreApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(StoreApplication.class, args);
//        var orderService = context.getBean(OrderService.class);
//        orderService.placeOrder();

//        var notificationService = context.getBean(NotificationService.class);
//        notificationService.send("This is the test message");
//
//        var userService = context.getBean(UserService.class);
//        userService.registerUser(new User(1L, "koshikpanchal@gmail.com", "1234", "koshik"));
//        userService.registerUser(new User(1L, "koshikpanchal@gmail.com", "1234", "koshik"));
    }

}
