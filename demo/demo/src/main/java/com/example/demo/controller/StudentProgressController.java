package com.example.demo.controller;

import com.example.demo.service.StudentProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/students/progress")
public class StudentProgressController {

    @Autowired
    private StudentProgressService studentProgressService;

    // Instructor only: Get overall progress for a student
    @GetMapping("/{studentId}")
    public ResponseEntity<Map<String, Object>> getStudentProgress(@PathVariable String studentId,
                                                                  @RequestParam String role) {
        try {
            Map<String, Object> progress = studentProgressService.getStudentProgress(studentId, role);
            return ResponseEntity.ok(progress);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
    }
}
