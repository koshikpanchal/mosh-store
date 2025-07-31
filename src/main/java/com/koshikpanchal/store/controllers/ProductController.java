package com.koshikpanchal.store.controllers;

import com.koshikpanchal.store.dtos.ProductDto;
import com.koshikpanchal.store.entity.Product;
import com.koshikpanchal.store.mapper.ProductMapper;
import com.koshikpanchal.store.repositories.CategoryRepository;
import com.koshikpanchal.store.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryRepository categoryRepository;

    @GetMapping
    public List<ProductDto> getAllProduct(
            @RequestParam( name="categoryId", required = false ) Byte categoryId
    ) {
        List<Product> products;
        if(categoryId != null) {
            products = productRepository.findByCategory_Id(categoryId);
        } else {
            products = productRepository.findAllWithCategory();
        }

        return products.stream().map(productMapper::toDto).toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id) {
        var product = productRepository.findById(id).orElse(null);
        if(product == null) {
            ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(productMapper.toDto(product));
    }

    @PostMapping
    public ResponseEntity<ProductDto> addProduct( @RequestBody ProductDto productDto ) {
        var category = categoryRepository.findById(productDto.getCategoryId()).orElse(null);
        if (category == null) {
            return ResponseEntity.badRequest().build();
        }

        var newProduct = productMapper.toEntity(productDto);
        newProduct.setCategory(category);
        productRepository.save(newProduct);
        productDto.setId(newProduct.getId());

        return ResponseEntity.ok(productDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(
            @PathVariable Long id,
            @RequestBody ProductDto productDto
    ) {
        var category = categoryRepository.findById(productDto.getCategoryId()).orElse(null);
        if (category == null) {
            return ResponseEntity.badRequest().build();
        }

        var product = productRepository.findById(id).orElse(null);
        if(product == null) {
            return ResponseEntity.notFound().build();
        }

        productMapper.update(productDto, product);
        productRepository.save(product);
        product.setCategory(category);
        productDto.setId(product.getId());

        return ResponseEntity.ok(productDto);
    }
}
