package com.examly.springapp.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.examly.springapp.entities.Language;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Long> {
    
    // ✅ Custom JPA method to find language by name
    Optional<Language> findByName(String name);

    // ✅ JPQL Query to find language by code
    @Query("SELECT l FROM Language l WHERE l.code = :code")
    Optional<Language> findByCode(@Param("code") String code);
}
