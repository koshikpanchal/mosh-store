package com.koshikpanchal.store;

import com.koshikpanchal.store.entity.User;
import com.koshikpanchal.store.repositories.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class StoreApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(StoreApplication.class, args);
        var repositories =  context.getBean(UserRepository.class);

        var user = User.builder().name("Koshik").email("kpanchal@ta.com").password("kpkpkp").build();

        repositories.save(user);
    }

}
