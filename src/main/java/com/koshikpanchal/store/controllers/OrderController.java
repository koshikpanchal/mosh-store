package com.koshikpanchal.store.controllers;

import com.koshikpanchal.store.dtos.ErrorDto;
import com.koshikpanchal.store.dtos.OrderDto;
import com.koshikpanchal.store.exceptions.OrderNotFoundException;
import com.koshikpanchal.store.services.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.List;

@RestController
@RequestMapping("/orders")
@AllArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping
    public List<OrderDto> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{orderId}")
    public OrderDto getOrder(@PathVariable("orderId") Long orderId) {
        return orderService.getOrder(orderId);
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<Void> handleExceptionNotFound() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorDto> handleExceptionDenied() {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorDto("Access Denied"));
    }
}
