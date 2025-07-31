package com.koshikpanchal.store.mosh;

public interface UserRepository {
    void save(User user);
    User findByEmail(String email);
}
