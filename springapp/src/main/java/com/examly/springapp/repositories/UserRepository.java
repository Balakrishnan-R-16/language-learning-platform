package com.examly.springapp.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.examly.springapp.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Custom JPA query method (Spring Data JPA naming convention)
    Optional<User> findByName(String name); // Find a user by exact name
    
    // Custom JPQL query to find users by name or email (case-insensitive)
    @Query("SELECT u FROM User u WHERE LOWER(u.name) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(u.email) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    List<User> searchUsers(@Param("searchTerm") String searchTerm);
}