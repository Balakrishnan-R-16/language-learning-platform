package com.examly.springapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.examly.springapp.entities.User;
import com.examly.springapp.repositories.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    // CRUD Operations
    public User createUser(User user) {
        return userRepository.save(user);
    }
    
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }
    
    public User updateUser(Long id, User userDetails) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("User not found"));
        user.setName(userDetails.getName());
        user.setEmail(userDetails.getEmail());
        user.setPreferredLanguage(userDetails.getPreferredLanguage());
        return userRepository.save(user);
    }
    
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("User not found"));
        userRepository.delete(user);
    }
    
    // Pagination and Sorting
    public Page<User> getUsersPaginated(int page, int size, String sortBy, boolean sortDir) {
        Sort sort = sortDir ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return userRepository.findAll(pageable);
    }
    
    // JPQL Search
    public List<User> searchUsers(String searchTerm) {
        return userRepository.searchUsers(searchTerm);
    }
    
    // Custom JPA Method
    public Optional<User> getUserByName(String name) {
        return userRepository.findByName(name);
    }
}