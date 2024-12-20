package com.example.demo.controller;

import com.example.demo.model.Question;
import com.example.demo.model.QuestionBank;
import com.example.demo.service.QuestionBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question-bank")
public class QuestionBankController {

    @Autowired
    private QuestionBankService questionBankService;

    // Add a question bank for a course (Instructor only)
    @PostMapping("/add/{courseId}")
    public ResponseEntity<String> addQuestionBank(@PathVariable String courseId,
                                                  @RequestBody QuestionBank questionBank,
                                                  @RequestHeader("Role") String role) {
        try {
            questionBankService.addQuestionBank(courseId, questionBank, role);
            return ResponseEntity.status(HttpStatus.CREATED).body("Question bank added successfully!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

    // Get all questions for a specific course (Student or Instructor)
    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<Question>> getQuestionsForCourse(@PathVariable String courseId,
                                                                @RequestHeader("Role") String role) {
        List<Question> questions = questionBankService.getQuestionsForCourse(courseId, role);
        if (questions != null) {
            return ResponseEntity.ok(questions);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
