package com.examly.springapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.examly.springapp.entities.Course;
import com.examly.springapp.repositories.CourseRepository;

@Service
public class CourseService {
    
    @Autowired
    private CourseRepository courseRepository;
    
    // CRUD: Create Course
    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }
    
    // CRUD: Read (Get all courses with pagination)
    public Page<Course> getAllCourses(int page, int size, String sortBy, String sortDirection) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        return courseRepository.findAll(pageable);
    }
    
    // CRUD: Read (Get course by ID)
    public Optional<Course> getCourseById(Long id) {
        return courseRepository.findById(id);
    }
    
    // CRUD: Update Course
    public Course updateCourse(Long id, Course courseDetails) {
        Course course = courseRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Course not found"));
        course.setTitle(courseDetails.getTitle());
        course.setLevel(courseDetails.getLevel());
        return courseRepository.save(course);
    }
    
    // CRUD: Delete Course
    public void deleteCourse(Long id) {
        Course course = courseRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Course not found"));
        courseRepository.delete(course);
    }

    // Custom JPA: Find Course by Title
    public Optional<Course> getCourseByTitle(String title) {
        return courseRepository.findByTitle(title);
    }

    // JPQL Query: Search Courses by title or level
    public List<Course> searchCourses(String searchTerm) {
        return courseRepository.searchCourses(searchTerm);
    }
}
