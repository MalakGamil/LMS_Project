package com.example.demo.controller;

import com.example.demo.model.Course;
import com.example.demo.model.Lesson;
import com.example.demo.service.CourseService;
import com.example.demo.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.example.demo.service.MediaService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    // Create a new course (Instructor only)
    @PostMapping
    public ResponseEntity<String> createCourse(@RequestBody Course course, @RequestParam String role) {
        try {
            courseService.createCourse(course, role);
            return ResponseEntity.status(HttpStatus.CREATED).body("Course created successfully!");
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
        }
    }

    // Get a course by ID
    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable String id) {
        try {
            Course course = courseService.getCourseById(id);
            return ResponseEntity.ok(course);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Get all courses
    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses() {
        List<Course> courses = courseService.getAllCourses();
        if (courses.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(courses);
    }

    // Enroll a student in a course (Instructor only)
    @PostMapping("/{courseId}/enroll")
    public ResponseEntity<String> enrollStudent(
            @PathVariable String courseId,
            @RequestParam String studentId,
            @RequestParam String role) {
        try {
            courseService.enrollStudent(courseId, studentId, role);
            return ResponseEntity.ok("Student enrolled successfully!");
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
        }
    }

    // Generate OTP for a lesson (Instructor only)
    @PostMapping("/{courseId}/lessons/{lessonId}/generate-otp")
    public ResponseEntity<String> generateOtpForLesson(
            @PathVariable String courseId,
            @PathVariable String lessonId,
            @RequestParam String role) {
        try {
            String otp = courseService.generateOtpForLesson(courseId, lessonId, role);
            return ResponseEntity.ok("OTP generated: " + otp);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
        }
    }

    // Validate OTP for attendance (Student only)
    @PostMapping("/{courseId}/lessons/{lessonId}/validate-otp")
    public ResponseEntity<String> validateOtpForAttendance(
            @PathVariable String courseId,
            @PathVariable String lessonId,
            @RequestParam String studentId,
            @RequestParam String enteredOtp,
            @RequestParam String role) {
        try {
            boolean isValid = courseService.validateOtpForAttendance(courseId, lessonId, studentId, enteredOtp, role);
            if (isValid) {
                return ResponseEntity.ok("Attendance marked successfully!");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid or expired OTP.");
            }
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
        }
    }

    // Add a media file to a course (Instructor only)
    @PostMapping("/{courseId}/add-media")
    public ResponseEntity<String> addMediaFile(
            @PathVariable String courseId,
            @RequestParam String mediaUrl,
            @RequestParam String role) {
        try {
            courseService.addMediaFile(courseId, mediaUrl, role);
            return ResponseEntity.status(HttpStatus.CREATED).body("Media file added successfully!");
        } catch (IllegalArgumentException | IOException ex) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
        }
    }

    // Update course details (Instructor only)
    @PutMapping("/{id}")
    public ResponseEntity<String> updateCourse(@PathVariable String id, @RequestBody Course updatedCourse, @RequestParam String role) {
        try {
            courseService.updateCourse(id, updatedCourse, role);
            return ResponseEntity.ok("Course updated successfully!");
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
        }
    }

    // Delete a course (Instructor only)
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable String id, @RequestParam String role) {
        try {
            courseService.deleteCourse(id, role);
            return ResponseEntity.ok("Course deleted successfully!");
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
        }
    }
}
