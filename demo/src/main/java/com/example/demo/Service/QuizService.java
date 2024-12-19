package com.example.demo.Service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Model.Course;
import com.example.demo.Model.Question;
import com.example.demo.Model.Quiz;
import com.example.demo.Model.UserRole;
import com.example.demo.Repository.CourseRepository;
import com.example.demo.Repository.QuizRepository;
@Service
public class QuizService {
    private final QuizRepository quizRepository;
    private final CourseRepository courseRepository;

    @Autowired
    public QuizService(QuizRepository quizRepository, CourseRepository courseRepository) {
        this.quizRepository = quizRepository;
        this.courseRepository = courseRepository;
    }

    public Quiz createQuiz(UserRole role, String id, String title, String courseId) {
        if (!isInstructor(role)) {
            throw new IllegalArgumentException("Only Instructors can create quizzes.");
        }

        if (!courseRepository.existsById(courseId)) {
            throw new IllegalArgumentException("No course with ID " + courseId + " exists.");
        }

        if (quizRepository.existsById(id)) {
            throw new IllegalArgumentException("A quiz with this ID already exists.");
        }

        return quizRepository.save(id, title, courseId);
    }

    public Collection<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
    }

    public void deleteQuiz(UserRole role, String quizId) {
        if (!isInstructor(role)) {
            throw new IllegalArgumentException("Only Instructors can delete quizzes.");
        }

        if (!quizRepository.existsById(quizId)) {
            throw new IllegalArgumentException("Quiz with ID " + quizId + " not found.");
        }

        quizRepository.delete(quizId);
    }

    public void updateQuiz(UserRole role, String quizId, Quiz updatedQuiz) {
        if (!isInstructor(role)) {
            throw new IllegalArgumentException("Only Instructors can update quizzes.");
        }

        if (!courseRepository.existsById(updatedQuiz.getCourseId())) {
            throw new IllegalArgumentException("No course with ID " + updatedQuiz.getCourseId() + " exists.");
        }

        if (!quizRepository.existsById(quizId)) {
            throw new IllegalArgumentException("Quiz with ID " + quizId + " does not exist.");
        }

        quizRepository.delete(quizId);
        quizRepository.save(updatedQuiz.getId(), updatedQuiz.getTitle(), updatedQuiz.getCourseId());
    }

    public void addQuestion(UserRole role, String quizId, Question question) {
        if (!isInstructor(role)) { // Only instructors can add questions
            throw new IllegalArgumentException("Only Instructors can add questions.");
        }
        Quiz quiz = quizRepository.findById(quizId); // Check if the quiz exists
        if (quiz == null) {
            throw new IllegalArgumentException("Quiz with ID " + quizId + " not found.");
        }
        quiz.addQuestion(question); // Add question to the quiz
        quizRepository.save(quiz); // Save updated quiz to the repository
    }
    public Quiz attemptQuiz(String quizId, int numberOfQuestions) {
        if (!quizRepository.existsById(quizId)) {
            throw new IllegalArgumentException("Quiz with ID " + quizId + " does not exist.");
        }
        Quiz quiz = quizRepository.findById(quizId);

        // Get the course associated with the quiz
        Course course = courseRepository.findById(quiz.getCourseId())
                .orElseThrow(() -> new IllegalArgumentException("Course for this quiz not found."));

        // Randomly select questions from the course's question bank
        quiz.selectRandomQuestions(course.getQuestionBank(), numberOfQuestions);

        return quiz; // Return the quiz with the randomly selected questions
    }

    private boolean isInstructor(UserRole role) {
        return role == UserRole.INSTRUCTOR;
    }
}

