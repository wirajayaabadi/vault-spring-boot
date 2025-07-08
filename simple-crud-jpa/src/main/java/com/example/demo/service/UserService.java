package com.example.demo.service;

import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.dto.UserDto;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.mapper.UserMapper;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                    .map(UserMapper.INSTANCE::toDto)
                    .collect(Collectors.toList());
    }

    public UserDto getUserById(UUID id) {
        User user = userRepository.findById(id)
                                .orElseThrow(() -> new ResourceNotFoundException(String.format("User not found with id: %s", id)));
        return UserMapper.INSTANCE.toDto(user);
    }

    public UserDto createUser(UserDto userDto) {
        User user = UserMapper.INSTANCE.toEntity(userDto);
        User savedUser = userRepository.save(user);
        return UserMapper.INSTANCE.toDto(savedUser);
    }

    public UserDto updateUser(UUID id, UserDto updatedUserDto) {
        User existingUser = userRepository.findById(id)
                                        .orElseThrow(() -> new ResourceNotFoundException(String.format("User not found with id: %s", id)));

        existingUser.setName(updatedUserDto.getName());
        existingUser.setEmail(updatedUserDto.getEmail());

        User updatedUser = userRepository.save(existingUser);
        return UserMapper.INSTANCE.toDto(updatedUser);

    }

    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }
}
