package com.example.demo.service;

import com.example.demo.model.Question;
import com.example.demo.model.QuestionBank;
import com.example.demo.repository.QuestionBankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionBankService {

    @Autowired
    private QuestionBankRepository questionBankRepository;

    // Add a question bank for a course (Instructor only)
    public void addQuestionBank(String courseId, QuestionBank questionBank, String role) {
        validateInstructorRole(role);
        questionBankRepository.addQuestionBank(courseId, questionBank);
    }

    // Get all questions for a specific course (Student or Instructor)
    public List<Question> getQuestionsForCourse(String courseId, String role) {
        QuestionBank questionBank = questionBankRepository.findByCourseId(courseId);
        if (questionBank != null) {
            return questionBank.getQuestions();
        }
        return null;
    }

    // Helper method to validate the instructor role
    private void validateInstructorRole(String role) {
        if (!"instructor".equalsIgnoreCase(role)) {
            throw new IllegalArgumentException("Only instructors can perform this action.");
        }
    }
}
