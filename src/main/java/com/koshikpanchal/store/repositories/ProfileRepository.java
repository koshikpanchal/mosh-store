package com.koshikpanchal.store.repositories;

import com.koshikpanchal.store.entity.Profile;
import org.springframework.data.repository.CrudRepository;

public interface ProfileRepository extends CrudRepository<Profile, Long> {
}
