package com.koshikpanchal.store.repositories;

import com.koshikpanchal.store.entity.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Byte> {
}
