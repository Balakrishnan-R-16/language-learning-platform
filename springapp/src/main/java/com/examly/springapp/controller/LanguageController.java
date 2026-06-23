package com.examly.springapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.entities.Language;
import com.examly.springapp.services.LanguageService;

@RestController
@RequestMapping("/api/languages")
public class LanguageController {
    
    @Autowired
    private LanguageService languageService;

    // ✅ Create a new language
    @PostMapping
    public ResponseEntity<Language> createLanguage(@RequestBody Language language) {
        return ResponseEntity.ok(languageService.createLanguage(language));
    }

    // ✅ Get all languages with pagination and sorting
    @GetMapping
    public ResponseEntity<Page<Language>> getAllLanguages(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy) {
        return ResponseEntity.ok(languageService.getAllLanguages(page, size, sortBy));
    }

    // ✅ Get language by ID
    @GetMapping("/{id}")
    public ResponseEntity<Language> getLanguageById(@PathVariable Long id) {
        return languageService.getLanguageById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // ✅ Update language by ID
    @PutMapping("/{id}")
    public ResponseEntity<Language> updateLanguage(@PathVariable Long id, @RequestBody Language languageDetails) {
        try {
            return ResponseEntity.ok(languageService.updateLanguage(id, languageDetails));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // ✅ Delete language by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLanguage(@PathVariable Long id) {
        try {
            languageService.deleteLanguage(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // ✅ Custom JPA: Get language by name
    @GetMapping("/name/{name}")
    public ResponseEntity<Language> getLanguageByName(@PathVariable String name) {
        return languageService.getLanguageByName(name)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // ✅ JPQL Query: Get language by code
    @GetMapping("/code/{code}")
    public ResponseEntity<Language> getLanguageByCode(@PathVariable String code) {
        return languageService.getLanguageByCode(code)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
