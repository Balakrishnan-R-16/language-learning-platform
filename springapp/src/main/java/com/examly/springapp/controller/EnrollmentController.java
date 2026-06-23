package com.examly.springapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.examly.springapp.entities.Enrollment;
import com.examly.springapp.services.EnrollmentService;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {
    
    @Autowired
    private EnrollmentService enrollmentService;

    // Get all enrollments with Pagination & Sorting
    @GetMapping
    public ResponseEntity<Page<Enrollment>> getAllEnrollments(Pageable pageable) {
        return ResponseEntity.ok(enrollmentService.getAllEnrollments(pageable));
    }

    // Get enrollments by progress percentage
    @GetMapping("/progress/{percentage}")
    public ResponseEntity<List<Enrollment>> getEnrollmentsByProgressPercentage(@PathVariable int percentage) {
        return ResponseEntity.ok(enrollmentService.getEnrollmentsByProgressPercentage(percentage));
    }

    // Get recent enrollments (Custom JPQL Query)
    @GetMapping("/recent")
    public ResponseEntity<List<Enrollment>> getRecentEnrollments() {
        return ResponseEntity.ok(enrollmentService.getRecentEnrollments());
    }

    // Get a specific enrollment by ID
    @GetMapping("/{id}")
    public ResponseEntity<Enrollment> getEnrollmentById(@PathVariable Long id) {
        return ResponseEntity.ok(enrollmentService.getEnrollmentById(id));
    }

    // Create a new enrollment
    @PostMapping
    public ResponseEntity<Enrollment> createEnrollment(@RequestBody Enrollment enrollment) {
        return ResponseEntity.ok(enrollmentService.saveEnrollment(enrollment));
    }

    // Update an existing enrollment
    @PutMapping("/{id}")
    public ResponseEntity<Enrollment> updateEnrollment(@PathVariable Long id, @RequestBody Enrollment updatedEnrollment) {
        return ResponseEntity.ok(enrollmentService.updateEnrollment(id, updatedEnrollment));
    }

    // Delete an enrollment
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEnrollment(@PathVariable Long id) {
        enrollmentService.deleteEnrollment(id);
        return ResponseEntity.noContent().build();
    }
}
