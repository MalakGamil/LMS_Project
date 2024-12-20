package com.example.demo.service;

import com.example.demo.model.Assessment;
import com.example.demo.repository.AssessmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssessmentService {

    @Autowired
    private AssessmentRepository assessmentRepository;

    public void createAssessment(Assessment assessment) {
        assessmentRepository.save(assessment);
    }

    public void submitAssessment(String assessmentId, String submission) {
        Assessment assessment = assessmentRepository.findById(assessmentId).orElseThrow(() -> new IllegalArgumentException("Assessment not found"));
        assessment.getSubmissions().add(submission);
        assessmentRepository.save(assessment);
    }
    public Assessment getAssessment(String assessmentId) {
        return assessmentRepository.findById(assessmentId)
                .orElseThrow(() -> new IllegalArgumentException("Assessment not found"));
    }
}