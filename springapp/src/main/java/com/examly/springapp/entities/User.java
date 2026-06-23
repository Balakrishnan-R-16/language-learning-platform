package com.examly.springapp.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Name cannot be blank")
    private String name;
    
    @Column(unique = true, nullable = false)
    @Email(message = "Invalid email format")
    @NotBlank(message = "Email cannot be blank")
    private String email;
    
    @NotBlank(message = "Password cannot be blank")
    private String password; // Consider hashing this in a real application
    
    private String preferredLanguage;
}