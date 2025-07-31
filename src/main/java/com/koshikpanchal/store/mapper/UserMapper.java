package com.koshikpanchal.store.mapper;

import com.koshikpanchal.store.dtos.RegisterUserRequest;
import com.koshikpanchal.store.dtos.UpdateUserRequest;
import com.koshikpanchal.store.dtos.UserDto;
import com.koshikpanchal.store.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);
    User toEntity(RegisterUserRequest request);
    void update(UpdateUserRequest request, @MappingTarget User user);
}
