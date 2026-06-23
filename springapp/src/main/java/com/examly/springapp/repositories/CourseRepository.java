package com.examly.springapp.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.examly.springapp.entities.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    // Custom JPA (Derived Query): Find Course by exact title
    Optional<Course> findByTitle(String title);

    // JPQL Query: Search courses by title or level (case-insensitive)
    @Query("SELECT c FROM Course c WHERE LOWER(c.title) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(c.level) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    List<Course> searchCourses(@Param("searchTerm") String searchTerm);
}
