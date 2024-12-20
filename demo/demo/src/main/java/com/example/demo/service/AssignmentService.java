package com.example.demo.service;

import com.example.demo.model.Assignment;
import com.example.demo.repository.AssignmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.util.concurrent.atomic.AtomicInteger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
public class AssignmentService {

    @Autowired
    private AssignmentRepository assignmentRepository;

    @Autowired
    private CourseService courseService;

    private static final String UPLOAD_DIR = "uploads/";
    private static final AtomicInteger idCounter = new AtomicInteger(1);

    // Instructor only: Create assignment
    public void createAssignment(Assignment assignment, String role) {
        if (!"instructor".equalsIgnoreCase(role)) {
            throw new IllegalArgumentException("Only instructors can create assignments.");
        }

        try {
            courseService.getCourseById(assignment.getCourseId());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Course not found. Cannot create assignment.");
        }

        assignment.setId(String.valueOf(idCounter.getAndIncrement()));

        assignmentRepository.save(assignment);
    }

    // Instructor only: Update assignment
    public void updateAssignment(Assignment assignment, String role) {
        if (!"instructor".equalsIgnoreCase(role)) {
            throw new IllegalArgumentException("Only instructors can update assignments.");
        }

        Assignment existingAssignment = assignmentRepository.findById(assignment.getId())
                .orElseThrow(() -> new IllegalArgumentException("Assignment not found"));

        existingAssignment.setTitle(assignment.getTitle());
        existingAssignment.setDescription(assignment.getDescription());
        existingAssignment.setDueDate(assignment.getDueDate());
        existingAssignment.setCourseId(assignment.getCourseId());

        assignmentRepository.save(existingAssignment);
    }
    // Instructor only: Delete assignment
    public void deleteAssignment(String id, String role) {
        if (!"instructor".equalsIgnoreCase(role)) {
            throw new IllegalArgumentException("Only instructors can delete assignments.");
        }

        Assignment assignment = assignmentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Assignment not found"));

        assignmentRepository.deleteById(id);
    }


    // Instructor or Student: Get assignments by course
    public List<Assignment> getAssignmentsByCourse(String courseId) {
        return assignmentRepository.findByCourseId(courseId);
    }

    // Instructor or Student: Save assignment file (for submission)
    public String saveAssignmentFile(MultipartFile file) throws IOException {
        File directory = new File(UPLOAD_DIR);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        String fileName = file.getOriginalFilename();
        String uniqueFileName = UUID.randomUUID().toString() + "-" + fileName;
        Path path = Paths.get(UPLOAD_DIR + uniqueFileName);
        Files.write(path, file.getBytes());

        return path.toString();
    }

    // Student only: Submit assignment
    public void submitAssignment(String id, String filePath, String studentId, String role) {
        if (!"student".equalsIgnoreCase(role)) {
            throw new IllegalArgumentException("Only students can submit assignments.");
        }

        Assignment assignment = assignmentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Assignment not found"));

        assignment.setFileUrl(filePath);
        assignment.setStudentId(studentId);
        assignmentRepository.save(assignment);
    }

    // Instructor only: Grade assignment
    public void gradeAssignment(String id, String grade, String feedback, String role) {
        if (!"instructor".equalsIgnoreCase(role)) {
            throw new IllegalArgumentException("Only instructors can grade assignments.");
        }

        Assignment assignment = assignmentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Assignment not found"));

        assignment.setGrade(grade);
        assignment.setFeedback(feedback);
        assignmentRepository.save(assignment);
    }

    public List<Assignment> getAssignmentsForStudent(String studentId, String role) {
        if (!"instructor".equalsIgnoreCase(role)) {
            throw new IllegalArgumentException("Only instructors can view assignment submissions.");
        }
        return assignmentRepository.findByStudentId(studentId);
    }
}
