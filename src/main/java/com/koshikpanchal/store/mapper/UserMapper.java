package com.koshikpanchal.store.mapper;

import com.koshikpanchal.store.dtos.RegisterUserRequest;
import com.koshikpanchal.store.dtos.UserDto;
import com.koshikpanchal.store.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);
    User toEntity(RegisterUserRequest request);
}
