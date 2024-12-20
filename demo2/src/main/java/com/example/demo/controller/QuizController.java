package com.example.demo.controller;

import com.example.demo.model.Quiz;
import com.example.demo.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/quizzes")
public class QuizController {

    @Autowired
    private QuizService quizService;

    // Create a new quiz (Instructors only)
    @PostMapping
    public ResponseEntity<String> createQuiz(
            @RequestBody Quiz quiz,
            @RequestParam String courseId,
            @RequestParam String role) {
        try {
            quizService.createQuiz(quiz, courseId, role);
            return ResponseEntity.status(HttpStatus.CREATED).body("Quiz created successfully!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

    // Get quizzes for a course
    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<Quiz>> getQuizzesByCourse(@PathVariable String courseId) {
        List<Quiz> quizzes = quizService.getQuizzesByCourse(courseId);
        return ResponseEntity.ok(quizzes);
    }

    // Attempt a quiz (Students only)
    @GetMapping("/{id}/attempt")
    public ResponseEntity<Quiz> attemptQuiz(
            @PathVariable String id,
            @RequestParam String studentId,
            @RequestParam String role) {
        try {
            Quiz attempt = quizService.attemptQuiz(id, studentId, role);
            return ResponseEntity.ok(attempt);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
    }

    // Submit quiz answers (Students only)
    @PutMapping("/{id}/submit")
    public ResponseEntity<String> submitQuiz(
            @PathVariable String id,
            @RequestBody Map<String, List<String>> requestBody,
            @RequestParam String studentId,
            @RequestParam String role) {
        // Extracting the list of answers from the request body
        List<String> submittedAnswers = requestBody.get("submittedAnswers");
        quizService.submitQuiz(id, studentId, submittedAnswers, role);

        return ResponseEntity.ok("Quiz submitted and graded successfully!");
    }


    // Delete a quiz by its ID (Instructors only)
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteQuiz(
            @PathVariable String id,
            @RequestParam String role) {
        try {
            quizService.deleteQuiz(id, role);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Quiz deleted successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

    // Instructor only: Get all quiz scores for a student
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Quiz>> getQuizScoresForStudent(@PathVariable String studentId,
                                                              @RequestParam String role) {
        try {
            List<Quiz> quizzes = quizService.getQuizScoresForStudent(studentId, role);
            return ResponseEntity.ok(quizzes);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
    }
}
