package com.example.demo.Controller;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Model.Question;
import com.example.demo.Model.Quiz;
import com.example.demo.Model.UserRole;
import com.example.demo.Service.QuizService;
 
@RestController
@RequestMapping("/api/quizzes")
public class QuizController {
    private final QuizService quizService;

    @Autowired
    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @PostMapping
    public ResponseEntity<String> createQuiz(
            @RequestBody Quiz quiz,
            @RequestHeader("User-Role") String role) {
        try {
            UserRole userRole = UserRole.valueOf(role.toUpperCase());
            quizService.createQuiz(userRole, quiz.getId(), quiz.getTitle(), quiz.getCourseId());
            return ResponseEntity.ok("Quiz created successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<Collection<Quiz>> getAllQuizzes() {
        return ResponseEntity.ok(quizService.getAllQuizzes());
    }

    @DeleteMapping("/{quizId}")
    public ResponseEntity<String> deleteQuiz(
            @PathVariable String quizId,
            @RequestHeader("User-Role") String role) {
        try {
            UserRole userRole = UserRole.valueOf(role.toUpperCase());
            quizService.deleteQuiz(userRole, quizId);
            return ResponseEntity.ok("Quiz deleted successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PutMapping("/{quizId}")
    public ResponseEntity<String> updateQuiz(
            @PathVariable String quizId,
            @RequestBody Quiz updatedQuiz,
            @RequestHeader("User-Role") String role) {
        try {
            UserRole userRole = UserRole.valueOf(role.toUpperCase());
            quizService.updateQuiz(userRole, quizId, updatedQuiz);
            return ResponseEntity.ok("Quiz updated successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
    @PostMapping("/{quizId}/questions")
    public ResponseEntity<String> addQuestion(
        @PathVariable String quizId,
        @RequestBody Question question,
        @RequestHeader("User-Role") String role) {
    try {
        UserRole userRole = UserRole.valueOf(role.toUpperCase()); // Validate user role
        quizService.addQuestion(userRole, quizId, question); // Add the question to the quiz
        return ResponseEntity.ok("Question added successfully to quiz with ID: " + quizId);
    } catch (IllegalArgumentException e) {
        return ResponseEntity.status(400).body(e.getMessage()); // Handle errors
    }
}

@PostMapping("/{quizId}/attempt")
    public ResponseEntity<Quiz> attemptQuiz(
            @PathVariable String quizId,
            @RequestParam int numberOfQuestions) {
        try {
            Quiz quiz = quizService.attemptQuiz(quizId, numberOfQuestions);
            return ResponseEntity.ok(quiz); // Return the quiz with random questions
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body(null);
        }
    }

}
