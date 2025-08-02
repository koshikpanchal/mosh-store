package com.koshikpanchal.store.mapper;

import com.koshikpanchal.store.dtos.CartDto;
import com.koshikpanchal.store.dtos.CartItemDto;
import com.koshikpanchal.store.entity.Cart;
import com.koshikpanchal.store.entity.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartMapper {
    @Mapping(target = "totalPrice", expression = "java(cart.getTotalPrice())")
    CartDto toDto(Cart cart);

    @Mapping(target = "totalPrice", expression = "java(cartItem.getTotalPrice())")
    CartItemDto toDto(CartItem cartItem);
}
