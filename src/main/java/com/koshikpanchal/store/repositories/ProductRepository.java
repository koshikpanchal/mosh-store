package com.koshikpanchal.store.repositories;

import com.koshikpanchal.store.entity.Product;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @EntityGraph(attributePaths = "category")
    List<Product> findByCategory_Id(Byte categoryId);

   @EntityGraph(attributePaths = "category")
   @Query("SELECT p FROM Product p")
   List<Product> findAllWithCategory();
}
