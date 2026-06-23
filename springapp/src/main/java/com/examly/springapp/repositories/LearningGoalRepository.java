package com.examly.springapp.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.examly.springapp.entities.LearningGoal;

@Repository
public interface LearningGoalRepository extends JpaRepository<LearningGoal, Long> {
    
    // Custom JPA method: Find goals before a specified date
    List<LearningGoal> findByTargetCompletionDateBefore(LocalDateTime date);

    // Custom method for Pagination
    Page<LearningGoal> findByOrderById(Pageable pageable);

    // JPQL query: Search for learning goals by name
    @Query("SELECT lg FROM LearningGoal lg WHERE lg.goalName LIKE %:keyword%")
    List<LearningGoal> searchGoalsByName(@Param("keyword") String keyword);
}
