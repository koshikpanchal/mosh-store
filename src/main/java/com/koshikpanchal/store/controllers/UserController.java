package com.koshikpanchal.store.controllers;

import com.koshikpanchal.store.dtos.RegisterUserRequest;
import com.koshikpanchal.store.dtos.UserDto;
import com.koshikpanchal.store.mapper.UserMapper;
import com.koshikpanchal.store.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @GetMapping
    public Iterable<UserDto> getAllUsers(
            @RequestParam(required = false, name= "sortBy", defaultValue = "") String sortBy
    ) {
        if (!Set.of("name", "email").contains(sortBy))
            sortBy = "name";
        return userRepository.findAll(Sort.by(sortBy))
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id) {
        var user = userRepository.findById(id).orElse(null);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(userMapper.toDto(user));
    }

    @PostMapping
    public ResponseEntity<UserDto> addUser(
            @RequestBody RegisterUserRequest request,
            UriComponentsBuilder uriComponentsBuilder
    ) {
        var user = userMapper.toEntity(request);
        userRepository.save(user);

        var userDto = userMapper.toDto(user);
        var uri = uriComponentsBuilder.path("/user/{id}").buildAndExpand(userDto.getId()).toUri();
        return ResponseEntity.created(uri).body(userDto);
    }
}
