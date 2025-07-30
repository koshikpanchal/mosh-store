package com.koshikpanchal.store.repositories;

import com.koshikpanchal.store.entity.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, Long> {
}
