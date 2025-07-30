package com.koshikpanchal.store.controllers;

import com.koshikpanchal.store.entity.Message;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    @RequestMapping("/hello")
    public Message sayHello() {
        return new Message("hello world");
    }
}
