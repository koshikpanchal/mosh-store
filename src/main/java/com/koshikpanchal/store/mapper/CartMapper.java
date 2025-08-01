package com.koshikpanchal.store.mapper;

import com.koshikpanchal.store.dtos.CartDto;
import com.koshikpanchal.store.entity.Cart;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CartMapper {
    CartDto toDto(Cart cart);
}
