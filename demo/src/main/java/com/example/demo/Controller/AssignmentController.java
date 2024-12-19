package com.example.demo.Controller;

import java.util.Collection;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.Model.Assignment;
import com.example.demo.Model.SubmitAssignmentRequest;
import com.example.demo.Model.UserRole;
import com.example.demo.Service.AssignmentService;

@RestController
@RequestMapping("/api/assignments")
public class AssignmentController {
    private AssignmentService assignmentService;

    public AssignmentController(AssignmentService assignmentService) {
        this.assignmentService = assignmentService;
    }

    @PostMapping
    public ResponseEntity<String> createAssignment(
            @RequestBody Assignment assignment,
            @RequestHeader("User-Role") String role) {
        try {
           UserRole userRole = UserRole.valueOf(role.toUpperCase());
           assignmentService.createAssignment(userRole,assignment.getId(), assignment.getTitle(), assignment.getDescription(), assignment.getCourseId());
           return ResponseEntity.ok("Assignment created successfully");
       }catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
    @GetMapping
    public ResponseEntity<Collection<Assignment>> getAllAssignments() {
        return ResponseEntity.ok(assignmentService.getAllAssignments());
    }

    @DeleteMapping("/{assignmentID}")
    public ResponseEntity<String> deleteAssignment(
            @PathVariable String assignmentID,
            @RequestHeader("User-Role") String role) {

        try {
            UserRole userRole = UserRole.valueOf(role.toUpperCase());

            // Call service to delete course
            assignmentService.deleteAssignment(userRole, assignmentID);

            return ResponseEntity.ok("Assignment deleted successfully.");
        }catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

     @PutMapping("/{assignmentID}")
    public ResponseEntity<String> updateCourse(
            @PathVariable String assignmentID,
            @RequestBody Assignment updatedAssignment,
            @RequestHeader("User-Role") String role) {

        try {
            UserRole userRole = UserRole.valueOf(role.toUpperCase());

            assignmentService.updateAssignment(assignmentID, updatedAssignment, userRole);

            return ResponseEntity.ok("Assignment updated successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body("Error: " + e.getMessage());
        }
    }

    @PostMapping("/submit")
    public ResponseEntity<String> submitAssignment( @RequestBody SubmitAssignmentRequest sar,
    @RequestHeader("User-Role") String role) {

    try {
        UserRole userRole = UserRole.valueOf(role.toUpperCase());

        assignmentService.submit(userRole, sar.getassignmentId(), sar.getStudent().getSubmittedFile(),sar.getStudent().getStudentId() ,sar.getCourseId());

        return ResponseEntity.ok("Assignment submited successfully.");
    } catch (IllegalArgumentException e) {
        return ResponseEntity.status(400).body("Error: " + e.getMessage());
    }
    }
}