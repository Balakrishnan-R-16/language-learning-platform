package com.examly.springapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.examly.springapp.entities.Enrollment;
import com.examly.springapp.repositories.EnrollmentRepository;

@Service
public class EnrollmentService {
    
    @Autowired
    private EnrollmentRepository enrollmentRepository;

    // Get all enrollments with Pagination & Sorting
    public Page<Enrollment> getAllEnrollments(Pageable pageable) {
        return enrollmentRepository.findAll(pageable);
    }

    // Get enrollments by progress percentage
    public List<Enrollment> getEnrollmentsByProgressPercentage(int progressPercentage) {
        return enrollmentRepository.findByProgressPercentage(progressPercentage);
    }

    // Get recent enrollments using JPQL
    public List<Enrollment> getRecentEnrollments() {
        return enrollmentRepository.findRecentEnrollments();
    }

    // Get enrollment by ID
    public Enrollment getEnrollmentById(Long id) {
        return enrollmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Enrollment not found with ID: " + id));
    }

    // Create or update an enrollment
    public Enrollment saveEnrollment(Enrollment enrollment) {
        return enrollmentRepository.save(enrollment);
    }

    // Update an existing enrollment
    public Enrollment updateEnrollment(Long id, Enrollment updatedEnrollment) {
        Optional<Enrollment> existingEnrollment = enrollmentRepository.findById(id);
        if (existingEnrollment.isPresent()) {
            Enrollment enrollment = existingEnrollment.get();
            enrollment.setProgressPercentage(updatedEnrollment.getProgressPercentage());
            enrollment.setEnrollmentDate(updatedEnrollment.getEnrollmentDate());
            return enrollmentRepository.save(enrollment);
        } else {
            throw new RuntimeException("Enrollment not found with ID: " + id);
        }
    }

    // Delete an enrollment
    public void deleteEnrollment(Long id) {
        enrollmentRepository.deleteById(id);
    }
}
