package com.example.demo.dto;

import java.util.UUID;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    
    private UUID id;

    @NotBlank(message = "Name is required")
    private String name;
    
    @NotBlank(message = "Username is required")
    private String username;

    @NotBlank(message = "Role is required")
    private String role;
    
    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    private String email;

}