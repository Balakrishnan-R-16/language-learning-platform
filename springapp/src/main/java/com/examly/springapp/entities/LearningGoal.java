package com.examly.springapp.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data // Lombok annotation to generate getters, setters, and constructors automatically
public class LearningGoal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generate ID values
    private Long id;

    private String goalName;
    private LocalDateTime targetCompletionDate; // Stores the completion date and time
}
