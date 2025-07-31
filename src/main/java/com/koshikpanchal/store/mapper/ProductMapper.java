package com.koshikpanchal.store.mapper;

import com.koshikpanchal.store.dtos.ProductDto;
import com.koshikpanchal.store.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(source = "category.id", target = "categoryId")
    ProductDto toDto(Product product);

    Product toEntity(ProductDto productDto);
}
