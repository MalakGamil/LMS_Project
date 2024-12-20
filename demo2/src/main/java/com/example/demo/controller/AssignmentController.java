package com.example.demo.controller;

import com.example.demo.model.Assignment;
import com.example.demo.service.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.Studentsubmission;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/assignments")
public class AssignmentController {

    @Autowired
    private AssignmentService assignmentService;

    // Create a new assignment - Instructor only
    @PostMapping
    public ResponseEntity<String> createAssignment(@RequestBody Assignment assignment,
            @RequestParam String role) {
        try {
            assignmentService.createAssignment(assignment, role);
            return ResponseEntity.status(HttpStatus.CREATED).body("Assignment created successfully!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

    // Update an assignment - Instructor only
    @PutMapping("/{id}")
    public ResponseEntity<String> updateAssignment(@PathVariable String id,
            @RequestBody Assignment assignment,
            @RequestParam String role) {
        try {
            assignment.setId(id); // Set the assignment ID for updating
            assignmentService.updateAssignment(assignment, role);
            return ResponseEntity.ok("Assignment updated successfully!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

    // Delete an assignment - Instructor only
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAssignment(@PathVariable String id,
            @RequestParam String role) {
        try {
            assignmentService.deleteAssignment(id, role);
            return ResponseEntity.ok("Assignment deleted successfully!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

    // Get assignments for a course - Available to both instructors and students
    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<Assignment>> getAssignmentsByCourse(@PathVariable String courseId) {
        List<Assignment> assignments = assignmentService.getAssignmentsByCourse(courseId);
        return ResponseEntity.ok(assignments);
    }

    // Submit an assignment - Student only
    @PostMapping("/{id}/submit")
    public ResponseEntity<String> submitAssignment(@PathVariable String id,
            @RequestPart("submittedfile") MultipartFile submittedfile, // Accept file upload
            @RequestParam String studentid,
            @RequestParam String role) {
        try {
            // Save the file and get the file path or URL
            String filePath = assignmentService.saveAssignmentFile(submittedfile,studentid,id);
            Studentsubmission studentsubmission = new Studentsubmission(studentid);
            // Call the service to handle the assignment submission
            assignmentService.submitAssignment(id, filePath, studentsubmission, role);
            return ResponseEntity.ok("Assignment submitted successfully!");
        } catch (IOException | IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

    // Grade an assignment - Instructor only
    @PutMapping("/{id}/grade")
    public ResponseEntity<String> gradeAssignment(@PathVariable String id,
            @RequestParam String studentid,
            @RequestParam String grade,
            @RequestParam String feedback,
            @RequestParam String role) {
        try {
            assignmentService.gradeAssignment(id, studentid, grade, feedback, role);
            return ResponseEntity.ok("Assignment graded successfully!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

    // Instructor only: Get all assignments for a student
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Assignment>> getAssignmentsForStudent(@PathVariable String studentId,
            @RequestParam String role) {
        try {
            List<Assignment> assignments = assignmentService.getAssignmentsForStudent(studentId, role);
            return ResponseEntity.ok(assignments);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
    }
}
