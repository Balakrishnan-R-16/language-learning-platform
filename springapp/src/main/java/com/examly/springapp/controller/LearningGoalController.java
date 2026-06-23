package com.examly.springapp.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.entities.LearningGoal;
import com.examly.springapp.services.LearningGoalService;

@RestController
@RequestMapping("/learning-goals")
public class LearningGoalController {

    @Autowired
    private LearningGoalService learningGoalService;

    // Get learning goals before a specific date
    @GetMapping("/before-date")
    public List<LearningGoal> getGoalsBeforeDate(@RequestParam LocalDateTime date) {
        return learningGoalService.getGoalsBeforeDate(date);
    }

    // Get paginated learning goals
    @GetMapping("/paginated")
    public Page<LearningGoal> getPaginatedLearningGoals(Pageable pageable) {
        return learningGoalService.getPaginatedLearningGoals(pageable);
    }

    // Search learning goals by name
    @GetMapping("/search")
    public List<LearningGoal> searchGoalsByName(@RequestParam String keyword) {
        return learningGoalService.searchGoalsByName(keyword);
    }

    // Create a new learning goal
    @PostMapping
    public LearningGoal createLearningGoal(@RequestBody LearningGoal learningGoal) {
        return learningGoalService.saveLearningGoal(learningGoal);
    }

    // Get a learning goal by ID
    @GetMapping("/{id}")
    public LearningGoal getLearningGoalById(@PathVariable Long id) {
        return learningGoalService.getLearningGoalById(id);
    }

    // Delete a learning goal by ID
    @DeleteMapping("/{id}")
    public void deleteLearningGoal(@PathVariable Long id) {
        learningGoalService.deleteLearningGoal(id);
    }
}
