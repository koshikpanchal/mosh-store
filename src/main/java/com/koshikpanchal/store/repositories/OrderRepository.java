package com.koshikpanchal.store.repositories;

import com.koshikpanchal.store.entity.Order;
import com.koshikpanchal.store.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @EntityGraph(attributePaths = "items.product")
    @Query("select o from Order o where o.customer = :customer")
    List<Order> getOrdersByCustomer(@Param("customer") User user);

    @EntityGraph(attributePaths = "items.product")
    @Query("select o from Order o where o.id = :orderId")
    Optional<Order> getOrderWithItems(@Param("orderId") Long orderId);
}
