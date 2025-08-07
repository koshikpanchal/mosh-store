package com.koshikpanchal.store.repositories;

import com.koshikpanchal.store.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
