package com.example.demo.service;

import com.example.demo.model.Lesson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudentProgressService {

    @Autowired
    private QuizService quizService;

    @Autowired
    private AssignmentService assignmentService;

    @Autowired
    private LessonService lessonService;

    // Instructor only: Get overall progress for a student
    public Map<String, Object> getStudentProgress(String studentId, String role) {
        if (!"instructor".equalsIgnoreCase(role)) {
            throw new IllegalArgumentException("Only instructors can view student progress.");
        }

        Map<String, Object> progress = new HashMap<>();

        // Get quiz scores for the student
        progress.put("quizzes", quizService.getQuizScoresForStudent(studentId, role));

        // Get assignments for the student
        progress.put("assignments", assignmentService.getAssignmentsForStudent(studentId, role));

        // Get lessons attended by the student
        List<Lesson> attendedLessons = lessonService.getLessonsByStudent(studentId);
        progress.put("attendedLessons", attendedLessons);

        return progress;
    }
}
