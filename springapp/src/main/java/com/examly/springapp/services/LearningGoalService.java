package com.examly.springapp.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.examly.springapp.entities.LearningGoal;
import com.examly.springapp.repositories.LearningGoalRepository;

@Service
public class LearningGoalService {

    @Autowired
    private LearningGoalRepository learningGoalRepository;

    // Fetch learning goals before a specific date
    public List<LearningGoal> getGoalsBeforeDate(LocalDateTime date) {
        return learningGoalRepository.findByTargetCompletionDateBefore(date);
    }

    // Fetch paginated learning goals
    public Page<LearningGoal> getPaginatedLearningGoals(Pageable pageable) {
        return learningGoalRepository.findByOrderById(pageable);
    }

    // Search learning goals by name
    public List<LearningGoal> searchGoalsByName(String keyword) {
        return learningGoalRepository.searchGoalsByName(keyword);
    }

    // Save a new learning goal
    public LearningGoal saveLearningGoal(LearningGoal learningGoal) {
        return learningGoalRepository.save(learningGoal);
    }

    // Fetch a learning goal by ID
    public LearningGoal getLearningGoalById(Long id) {
        return learningGoalRepository.findById(id).orElse(null);
    }

    // Delete a learning goal by ID
    public void deleteLearningGoal(Long id) {
        learningGoalRepository.deleteById(id);
    }
}
