package com.examly.springapp.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.examly.springapp.entities.Enrollment;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    
    // Custom JPA method (Simple query method)
    List<Enrollment> findByProgressPercentage(int progressPercentage);

    // JPQL Query - Get recent enrollments (last 30 days)
    @Query("SELECT e FROM Enrollment e ORDER BY e.enrollmentDate DESC")
    List<Enrollment> findRecentEnrollments();


    // Pagination & Sorting for enrollments
    @Override
    Page<Enrollment> findAll(Pageable pageable);
}
