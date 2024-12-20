package com.example.demo.controller;

import com.example.demo.model.Lesson;
import com.example.demo.service.LessonService;
import com.example.demo.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses/{courseId}/lessons")
public class LessonController {

    @Autowired
    private LessonService lessonService;



    // Get all lessons for a specific course
    @GetMapping
    public ResponseEntity<List<Lesson>> getAllLessons(@PathVariable String courseId) {
        List<Lesson> lessons = lessonService.getAllLessonsByCourseId(courseId);
        if (lessons.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(lessons);
    }

    @PostMapping
    public ResponseEntity<String> createLesson(
            @PathVariable String courseId,
            @RequestBody Lesson lesson,
            @RequestParam String role) {
        try {
            lessonService.createLesson(courseId, lesson, role);
            return ResponseEntity.status(HttpStatus.CREATED).body("Lesson created successfully!");
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
        }
    }
    // Mark attendance for a student using OTP (Student only)
    @PostMapping("/{lessonId}/mark-attendance")
    public ResponseEntity<String> markAttendance(
            @PathVariable String courseId,
            @PathVariable String lessonId,
            @RequestParam String studentId,
            @RequestParam String enteredOtp,
            @RequestParam String role) {
        try {
            boolean isMarked = lessonService.markAttendance(courseId, lessonId, studentId, enteredOtp, role);
            return isMarked ? ResponseEntity.ok("Attendance marked successfully!") : ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid OTP or OTP expired.");
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
        }
    }

    // Get a specific lesson by ID
    @GetMapping("/{lessonId}")
    public ResponseEntity<Lesson> getLessonById(@PathVariable String courseId, @PathVariable String lessonId) {
        try {
            // Fetch lesson based on courseId and lessonId
            Lesson lesson = lessonService.getLessonById(courseId, lessonId);
            if (lesson != null) {
                return ResponseEntity.ok(lesson);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Update an existing lesson (Instructor only)
    @PutMapping("/{lessonId}")
    public ResponseEntity<String> updateLesson(
            @PathVariable String courseId,
            @PathVariable String lessonId,
            @RequestBody Lesson updatedLesson,
            @RequestParam String role) {
        try {
            lessonService.updateLesson(courseId, lessonId, updatedLesson, role);
            return ResponseEntity.ok("Lesson updated successfully!");
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
        }
    }

    // Delete a lesson (Instructor only)
    @DeleteMapping("/{lessonId}")
    public ResponseEntity<String> deleteLesson(
            @PathVariable String courseId,
            @PathVariable String lessonId,
            @RequestParam String role) {
        try {
            lessonService.deleteLesson(courseId, lessonId, role);
            return ResponseEntity.ok("Lesson deleted successfully!");
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
        }
    }
}




