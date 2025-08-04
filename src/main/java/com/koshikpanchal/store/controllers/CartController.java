package com.koshikpanchal.store.controllers;

import com.koshikpanchal.store.dtos.AddItemToCartRequest;
import com.koshikpanchal.store.dtos.CartDto;
import com.koshikpanchal.store.dtos.CartItemDto;
import com.koshikpanchal.store.dtos.UpdateCartItemRequest;
import com.koshikpanchal.store.entity.Cart;
import com.koshikpanchal.store.mapper.CartMapper;
import com.koshikpanchal.store.repositories.CartRepository;
import com.koshikpanchal.store.repositories.ProductRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/carts")
@AllArgsConstructor
public class CartController {
    private final CartRepository cartRepository;
    private final CartMapper cartMapper;
    private final ProductRepository productRepository;

    @PostMapping
    public ResponseEntity<CartDto> createCart() {
        var cart = new Cart();
        cartRepository.save(cart);
        var cartDto = cartMapper.toDto(cart);

        return new ResponseEntity<>(cartDto, HttpStatus.CREATED);
    }

    @PostMapping("/{cartId}/items")
    public ResponseEntity<CartItemDto> addToCart(
            @PathVariable("cartId") UUID cartId,
            @RequestBody AddItemToCartRequest request
    ) {
        var cart = cartRepository.getCartWithItems(cartId).orElse(null);
        if(cart == null) {
            return ResponseEntity.notFound().build();
        }

        var product = productRepository.findById(request.getProductId()).orElse(null);
        if(product == null) {
            return ResponseEntity.badRequest().build();
        }

        var cartItem = cart.addItem(product);

        cartRepository.save(cart);
        var cartItemDto = cartMapper.toDto(cartItem);

        return ResponseEntity.status(HttpStatus.CREATED).body(cartItemDto);
    }

    @GetMapping("/{cartId}")
    public ResponseEntity<CartDto> getCart(
            @PathVariable("cartId") UUID cartId
    ) {
        var cart = cartRepository.getCartWithItems(cartId).orElse(null);
        if(cart == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(cartMapper.toDto(cart));
    }

    @PutMapping("/{cartId}/items/{productId}")
    public ResponseEntity<?> updateCartItem(
            @PathVariable("cartId") UUID cartId,
            @PathVariable("productId") Long productId,
            @Valid @RequestBody UpdateCartItemRequest request
    ) {
        var cart = cartRepository.getCartWithItems(cartId).orElse(null);
        if(cart == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    Map.of("error", "cart not found")
            );
        }

        var cartItem = cart.getItem(productId);

        if(cartItem == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    Map.of("error", "product was not found in the cart")
            );
        }
        cartItem.setQuantity(request.getQuantity());
        cartRepository.save(cart);

        return ResponseEntity.ok(cartMapper.toDto(cartItem));
    }

    @DeleteMapping("/{cartId}/items/{productId}")
    public ResponseEntity<?> removeItem(
            @PathVariable("cartId") UUID cartId,
            @PathVariable("productId") Long productId
    ) {
        var cart = cartRepository.getCartWithItems(cartId).orElse(null);
        if(cart == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    Map.of("error", "cart was not found")
            );
        }
        cart.removeItem(productId);

        cartRepository.save(cart);

        return ResponseEntity.noContent().build();
    }
}
