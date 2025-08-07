package com.koshikpanchal.store.mapper;

import com.koshikpanchal.store.dtos.OrderDto;
import com.koshikpanchal.store.entity.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderDto toDto(Order order);
}
