package com.example.demo.repository;

import com.example.demo.model.Quiz;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class QuizRepository {
    // Simulating an in-memory database with a map
    private final Map<String, Quiz> quizzes = new HashMap<>();
    private int nextId = 1;  // To track the next available quiz ID

    // Save a quiz
    public void save(Quiz quiz) {
        quiz.setId(String.valueOf(nextId));  // Assign the next sequential ID
        quizzes.put(quiz.getId(), quiz);
        nextId++;  // Increment to the next ID for the next quiz
    }

    // Find a quiz by its ID
    public Optional<Quiz> findById(String id) {
        return Optional.ofNullable(quizzes.get(id));
    }

    // Get all quizzes for a course
    public List<Quiz> findByCourseId(String courseId) {
        List<Quiz> result = new ArrayList<>();
        for (Quiz quiz : quizzes.values()) {
            if (quiz.getCourseId().equals(courseId)) {
                result.add(quiz);
            }
        }
        return result;
    }

    // Get all quizzes submitted by a student
    public List<Quiz> findByStudentId(String studentId) {
        List<Quiz> result = new ArrayList<>();
        for (Quiz quiz : quizzes.values()) {
            if (quiz.getStudentId().equals(studentId)) {
                result.add(quiz);
            }
        }
        return result;
    }

    // Delete a quiz by ID
    public void delete(String id) {
        quizzes.remove(id);
    }

    // Optional method to get the next ID (in case needed elsewhere)
    public int getNextId() {
        return nextId;
    }
}
