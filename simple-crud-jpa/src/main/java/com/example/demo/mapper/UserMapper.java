package com.example.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.demo.dto.UserDto;
import com.example.demo.model.User;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    // Map Entity to DTO
    UserDto toDto(User user);

    // Map DTO to Entity
    User toEntity(UserDto userDto);
}
