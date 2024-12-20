package com.example.demo.service;

import com.example.demo.model.Lesson;
import com.example.demo.repository.LessonRepository;
import com.example.demo.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LessonService {

    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private CourseRepository courseRepository;

    // Create a new lesson for a specific course (Instructor only)
    public void createLesson(String courseId, Lesson lesson, String role) {
        validateInstructorRole(role);

        if (!courseRepository.findById(courseId).isPresent()) {
            throw new IllegalArgumentException("Course not found.");
        }
        lessonRepository.save(courseId, lesson);
    }

    // Retrieve all lessons for a specific course
    public List<Lesson> getAllLessonsByCourseId(String courseId) {
        return lessonRepository.findAllLessonsByCourseId(courseId)
                .values()
                .stream()
                .collect(Collectors.toList());
    }

    // Retrieve a specific lesson by courseId and lessonId
    public Lesson getLessonById(String courseId, String lessonId) {
        Lesson lesson = lessonRepository.findLessonByCourseAndLessonId(courseId, lessonId);
        if (lesson == null) {
            throw new IllegalArgumentException("Lesson not found.");
        }
        return lesson;
    }

    // Check if OTP has expired
    public boolean isOtpExpired(Lesson lesson) {
        LocalDateTime otpGeneratedDateTime = Instant.ofEpochMilli(lesson.getOtpGeneratedTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
        return otpGeneratedDateTime.plusMinutes(30).isBefore(LocalDateTime.now());
    }

    // Generate OTP for a lesson (Instructor only)
    public Lesson generateOtpForLesson(String courseId, String lessonId, String role) {
        validateInstructorRole(role);

        Lesson lesson = getLessonById(courseId, lessonId);

        // Generate OTP
        String otp = String.valueOf((int) (Math.random() * 900000) + 100000); // 6-digit OTP
        lesson.setOtp(otp);
        lesson.setOtpGeneratedTime(System.currentTimeMillis());

        // Save updated lesson
        lessonRepository.save(courseId, lesson);
        return lesson;
    }

    // Mark attendance for a student using OTP
    public boolean markAttendance(String courseId, String lessonId, String studentId, String enteredOtp, String role) {
        validateStudentRole(role);

        Lesson lesson = getLessonById(courseId, lessonId);

        // Check if OTP is expired
        if (isOtpExpired(lesson)) {
            throw new IllegalArgumentException("OTP has expired.");
        }

        // Validate entered OTP
        if (!lesson.getOtp().equals(enteredOtp)) {
            throw new IllegalArgumentException("Invalid OTP.");
        }

        // Mark attendance
        lesson.markAttendance(studentId);
        lessonRepository.save(courseId, lesson);
        return true;
    }

    public List<Lesson> getLessonsByStudent(String studentId) {
        // Get all lessons across all courses
        Map<String, Map<String, Lesson>> allLessons = lessonRepository.findAll();

        // Flatten the map and filter lessons where the student has attended
        return allLessons.values().stream()
                .flatMap(courseLessons -> courseLessons.values().stream()) // Flatten all lessons
                .filter(lesson -> lesson.isStudentAttended(studentId)) // Filter lessons attended by student
                .collect(Collectors.toList());
    }

    // Update an existing lesson (Instructor only)
    public void updateLesson(String courseId, String lessonId, Lesson updatedLesson, String role) {
        validateInstructorRole(role);

        if (!courseRepository.findById(courseId).isPresent()) {
            throw new IllegalArgumentException("Course not found.");
        }

        Lesson existingLesson = getLessonById(courseId, lessonId);
        existingLesson.setLessonTitle(updatedLesson.getLessonTitle());
        existingLesson.setLessonContent(updatedLesson.getLessonContent());
        lessonRepository.save(courseId, existingLesson);
    }

    // Delete an existing lesson (Instructor only)
    public void deleteLesson(String courseId, String lessonId, String role) {
        validateInstructorRole(role);

        if (!courseRepository.findById(courseId).isPresent()) {
            throw new IllegalArgumentException("Course not found.");
        }

        Lesson lesson = getLessonById(courseId, lessonId);
        lessonRepository.delete(courseId, lessonId);
    }
    // Helper method to validate the instructor role
    private void validateInstructorRole(String role) {
        if (!"instructor".equalsIgnoreCase(role)) {
            throw new IllegalArgumentException("Only instructors can perform this action.");
        }
    }

    // Helper method to validate the student role
    private void validateStudentRole(String role) {
        if (!"student".equalsIgnoreCase(role)) {
            throw new IllegalArgumentException("Only students can perform this action.");
        }
    }
}
