package com.example.demo.service;

import com.example.demo.model.Quiz;
import com.example.demo.model.Question;
import com.example.demo.repository.QuizRepository;
import com.example.demo.repository.QuestionBankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuizService {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuestionBankRepository questionBankRepository;

    @Autowired
    private CourseService courseService;

    private static final int NUMBER_OF_QUESTIONS = 5; // Fixed number of questions

    // Create a new quiz (Instructor-only)
    public void createQuiz(Quiz quiz, String courseId, String role) {
        if (!"instructor".equalsIgnoreCase(role)) {
            throw new IllegalArgumentException("Only instructors can create quizzes.");
        }

        // Ensure the course exists
        courseService.getCourseById(courseId);

        // Fetch a random subset of questions from the question bank
        List<Question> questionBank = questionBankRepository.findByCourseId(courseId).getQuestions();
        if (questionBank.isEmpty()) {
            throw new IllegalArgumentException("No questions available in the question bank for this course.");
        }

        // Randomize and limit the number of questions for the quiz
        Collections.shuffle(questionBank);
        List<Question> selectedQuestions = questionBank.stream()
                .limit(NUMBER_OF_QUESTIONS)
                .collect(Collectors.toList());

        quiz.setQuestions(selectedQuestions);
        quiz.setCourseId(courseId);

        // Save the quiz
        quizRepository.save(quiz);
    }

    // Get quizzes by course ID
    public List<Quiz> getQuizzesByCourse(String courseId) {
        // Ensure the course exists
        courseService.getCourseById(courseId);

        return quizRepository.findByCourseId(courseId);
    }

    // Attempt a quiz (Student-only)
    public Quiz attemptQuiz(String quizId, String studentId, String role) {
        if (!"student".equalsIgnoreCase(role)) {
            throw new IllegalArgumentException("Only students can attempt quizzes.");
        }

        // Fetch the quiz
        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new IllegalArgumentException("Quiz not found"));

        // Fetch the original questions from the quiz's course
        List<Question> questionBank = questionBankRepository.findByCourseId(quiz.getCourseId()).getQuestions();
        if (questionBank.isEmpty()) {
            throw new IllegalArgumentException("No questions available in the question bank for this course.");
        }

        // Randomize and limit the number of questions for this attempt
        Collections.shuffle(questionBank);
        List<Question> randomizedQuestions = questionBank.stream()
                .limit(NUMBER_OF_QUESTIONS)
                .collect(Collectors.toList());

        // Create a new Quiz instance for the attempt
        Quiz attempt = new Quiz();
        attempt.setId(quizId);
        attempt.setStudentId(studentId);
        attempt.setQuestions(randomizedQuestions);

        return attempt;
    }



    // Submit a quiz (grade it and provide feedback) - Students only
    public void submitQuiz(String quizId, String studentId, List<String> submittedAnswers, String role) {
        if (!"student".equalsIgnoreCase(role)) {
            throw new IllegalArgumentException("Only students can submit quizzes.");
        }
        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new IllegalArgumentException("Quiz not found"));

        if (!quiz.getStudentId().equals(studentId)) {
            throw new IllegalArgumentException("You are not authorized to submit this quiz.");
        }
        quiz.setSubmittedAnswers(submittedAnswers);
        quiz.setStudentId(studentId);
        gradeQuiz(quiz, submittedAnswers);
    }


    // Grade quiz (used for attempts and final grading)
    private void gradeQuiz(Quiz quiz, List<String> submittedAnswers) {
        int score = 0;
        List<Question> questions = quiz.getQuestions();

        // Auto-grade based on submitted answers
        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i);
            String correctAnswer = question.getCorrectAnswer();
            String studentAnswer = i < submittedAnswers.size() ? submittedAnswers.get(i) : "";

            if (correctAnswer.equals(studentAnswer)) {
                score++;
            }
        }

        int totalQuestions = questions.size();
        quiz.setGrade(score + "/" + totalQuestions);

        // Provide feedback
        String feedback = (score * 100 / totalQuestions) >= 70
                ? "Great job! Keep up the good work."
                : "You need more practice. Try again!";
        quiz.setFeedback(feedback);
    }

    // Delete a quiz (Instructor-only)
    public void deleteQuiz(String quizId, String role) {
        if (!"instructor".equalsIgnoreCase(role)) {
            throw new IllegalArgumentException("Only instructors can delete quizzes.");
        }

        quizRepository.findById(quizId).ifPresentOrElse(
                quiz -> quizRepository.delete(quizId),
                () -> {
                    throw new IllegalArgumentException("Quiz not found");
                });
    }

    // Instructor only: Get all quiz scores for a student
    public List<Quiz> getQuizScoresForStudent(String studentId, String role) {
        if (!"instructor".equalsIgnoreCase(role)) {
            throw new IllegalArgumentException("Only instructors can view quiz scores.");
        }
        return quizRepository.findByStudentId(studentId);
    }
}
