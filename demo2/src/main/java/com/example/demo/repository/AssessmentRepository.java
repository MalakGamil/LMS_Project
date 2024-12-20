package com.example.demo.repository;
import com.example.demo.model.Assessment;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class AssessmentRepository {

    // In-memory map to simulate a database
    private final Map<String, Assessment> assessmentStorage = new HashMap<>();

    // Save an assessment
    public void save(Assessment assessment) {
        assessmentStorage.put(assessment.getId(), assessment);
    }

    // Find an assessment by its ID
    public Optional<Assessment> findById(String id) {
        return Optional.ofNullable(assessmentStorage.get(id));
    }

    // Retrieve all assessments
    public Map<String, Assessment> findAll() {
        return assessmentStorage;
    }
}