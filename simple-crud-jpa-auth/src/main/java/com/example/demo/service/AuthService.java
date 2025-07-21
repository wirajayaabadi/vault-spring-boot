package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.UserRepository;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    public boolean login(String username, String password) {
        var user = userRepository.findByUsername(username);
        return user.isPresent() && checkPassword(password, user.get().getPassword());
    }

    private boolean checkPassword(String password, String passwordHash) {
        return password.equals(passwordHash);
    }
}
