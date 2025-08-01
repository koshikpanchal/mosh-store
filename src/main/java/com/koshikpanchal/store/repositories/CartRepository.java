package com.koshikpanchal.store.repositories;

import com.koshikpanchal.store.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CartRepository extends JpaRepository<Cart, UUID> {
}