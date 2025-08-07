package com.koshikpanchal.store.services;

import com.koshikpanchal.store.dtos.OrderDto;
import com.koshikpanchal.store.mapper.OrderMapper;
import com.koshikpanchal.store.repositories.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private AuthService authService;

    public List<OrderDto> getAllOrders() {
        var user = authService.getCurrentUser();
        var order = orderRepository.getAllByCustomer(user);
        return order.stream().map(orderMapper::toDto).toList();
    }
}
