package com.example.demo.controller;

import com.example.demo.model.Assessment;
import com.example.demo.service.AssessmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/assessments")
public class AssessmentController {

    @Autowired
    private AssessmentService assessmentService;

    // Create a new assessment
    @PostMapping
    public String createAssessment(@RequestBody Assessment assessment) {
        assessmentService.createAssessment(assessment);
        return "Assessment created successfully!";
    }

    // Submit an assessment
    @PostMapping("/{id}/submit")
    public String submitAssessment(@PathVariable String id, @RequestParam String submission) {
        assessmentService.submitAssessment(id, submission);
        return "Assessment submitted successfully!";
    }

    // Get an assessment by its ID
    @GetMapping("/{id}")
    public Assessment getAssessment(@PathVariable String id) {
        return assessmentService.getAssessment(id);
    }
}
