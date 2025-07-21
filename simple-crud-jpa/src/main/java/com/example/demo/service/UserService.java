package com.example.demo.service;

import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.dto.UserRequest;
import com.example.demo.dto.UserResponse;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.mapper.UserRequestMapper;
import com.example.demo.mapper.UserResponseMapper;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    public List<UserResponse> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                    .map(UserResponseMapper.INSTANCE::toDto)
                    .collect(Collectors.toList());
    }

    public UserResponse getUserById(UUID id) {
        User user = userRepository.findById(id)
                                .orElseThrow(() -> new ResourceNotFoundException(String.format("User not found with id: %s", id)));
        return UserResponseMapper.INSTANCE.toDto(user);
    }

    public UserResponse createUser(UserRequest userDto) {
        User user = UserRequestMapper.INSTANCE.toEntity(userDto);
        User savedUser = userRepository.save(user);
        return UserResponseMapper.INSTANCE.toDto(savedUser);
    }

    public UserResponse updateUser(UUID id, UserRequest updatedUserDto) {
        User existingUser = userRepository.findById(id)
                                        .orElseThrow(() -> new ResourceNotFoundException(String.format("User not found with id: %s", id)));

        existingUser.setName(updatedUserDto.getName());
        existingUser.setEmail(updatedUserDto.getEmail());

        User updatedUser = userRepository.save(existingUser);
        return UserResponseMapper.INSTANCE.toDto(updatedUser);

    }

    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }
}
