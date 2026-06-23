package com.examly.springapp.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.examly.springapp.entities.Language;
import com.examly.springapp.repositories.LanguageRepository;

@Service
public class LanguageService {
    
    @Autowired
    private LanguageRepository languageRepository;

    // ✅ Create a new language
    public Language createLanguage(Language language) {
        return languageRepository.save(language);
    }

    // ✅ Get all languages with pagination and sorting
    public Page<Language> getAllLanguages(int page, int size, String sortBy) {
        return languageRepository.findAll(PageRequest.of(page, size, Sort.by(sortBy)));
    }

    // ✅ Get language by ID
    public Optional<Language> getLanguageById(Long id) {
        return languageRepository.findById(id);
    }

    // ✅ Update language details
    public Language updateLanguage(Long id, Language languageDetails) {
        Language language = languageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Language not found"));
        language.setName(languageDetails.getName());
        language.setCode(languageDetails.getCode());
        return languageRepository.save(language);
    }

    // ✅ Delete language by ID
    public void deleteLanguage(Long id) {
        Language language = languageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Language not found"));
        languageRepository.delete(language);
    }

    // ✅ Custom JPA: Find language by name
    public Optional<Language> getLanguageByName(String name) {
        return languageRepository.findByName(name);
    }

    // ✅ JPQL Query: Find language by code
    public Optional<Language> getLanguageByCode(String code) {
        return languageRepository.findByCode(code);
    }
}
