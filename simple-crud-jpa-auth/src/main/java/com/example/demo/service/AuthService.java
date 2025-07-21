package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.util.JwtUtil;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private JwtUtil jwtUtil;

    public String register(String username, String password) {
        if (userRepository.existsByUsername(username)) return "User already exists";
        User user = new User();
        user.setUsername(username);
        user.setPassword(encoder.encode(password));
        user.setRole("USER");
        userRepository.save(user);
        return "Registered user successfully";
    }

    public String login(String username, String password) {
        User user = userRepository.findByUsername(username).get();
        if (!encoder.matches(password, user.getPassword())) return "Invalid credentials";

        return jwtUtil.generateToken(user);
    }

}
