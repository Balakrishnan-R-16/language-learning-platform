package com.examly.springapp.controller;

import java.util.List;

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

import com.examly.springapp.entities.Course;
import com.examly.springapp.services.CourseService;

@RestController
@RequestMapping("/api/courses")
public class CourseController {
    
    @Autowired
    private CourseService courseService;
    
    // CRUD: Create Course
    @PostMapping
    public ResponseEntity<Course> createCourse(@RequestBody Course course) {
        return ResponseEntity.ok(courseService.createCourse(course));
    }
    
    // CRUD: Read (Get all courses with pagination)
    @GetMapping
    public ResponseEntity<Page<Course>> getAllCourses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "title") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDirection) {
        return ResponseEntity.ok(courseService.getAllCourses(page, size, sortBy, sortDirection));
    }
    
    // CRUD: Read (Get course by ID)
    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long id) {
        return courseService.getCourseById(id)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    // CRUD: Update Course
    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable Long id, @RequestBody Course courseDetails) {
        try {
            return ResponseEntity.ok(courseService.updateCourse(id, courseDetails));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    // CRUD: Delete Course
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        try {
            courseService.deleteCourse(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Custom JPA: Find Course by Title (Derived Query)
    @GetMapping("/title/{title}")
    public ResponseEntity<Course> getCourseByTitle(@PathVariable String title) {
        return courseService.getCourseByTitle(title)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // JPQL Query: Search Courses by title or level (partial match)
    @GetMapping("/search")
    public ResponseEntity<List<Course>> searchCourses(@RequestParam String searchTerm) {
        return ResponseEntity.ok(courseService.searchCourses(searchTerm));
    }
}
