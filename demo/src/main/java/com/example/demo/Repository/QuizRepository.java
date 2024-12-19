package com.example.demo.Repository;

import com.example.demo.Model.Quiz;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class QuizRepository {
    private final Map<String, Quiz> quizzes = new HashMap<>();

    public Quiz save(String id, String title, String courseId) {
        Quiz quiz = new Quiz(id, title, courseId);
        quizzes.put(quiz.getId(), quiz);
        return quiz;
    }

    public Quiz save(Quiz quiz) {
        quizzes.put(quiz.getId(), quiz); // Update quiz in the repository
        return quiz;
    }
    

    public Collection<Quiz> findAll() {
        return quizzes.values();
    }

    public Quiz findById(String id) {
        return quizzes.get(id);
    }

    public boolean existsById(String id) {
        return quizzes.containsKey(id);
    }

    public void delete(String id) {
        quizzes.remove(id);
    }
}