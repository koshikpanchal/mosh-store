package com.koshikpanchal.store.repositories;

import com.koshikpanchal.store.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

}
