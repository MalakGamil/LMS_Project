package com.example.demo.Service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Model.Assignment;
import com.example.demo.Model.UserRole;
import com.example.demo.Repository.AssignmentRepository;
import com.example.demo.Repository.CourseRepository;

@Service
public class AssignmentService {
    private AssignmentRepository assignmentRepository;
    private final CourseRepository courseRepository;

    @Autowired
    public AssignmentService(AssignmentRepository assignmentRepository, CourseRepository courseRepository) {
        this.assignmentRepository = assignmentRepository;
        this.courseRepository = courseRepository;
    }

    public Assignment createAssignment(UserRole role, String id, String title, String description, String courseId) {
        if (!isInstructor(role)) {
            throw new IllegalArgumentException("Only Instructors can create Assignment.");
        }
        if (!courseRepository.existsById(courseId)) {
            throw new IllegalArgumentException("There is no course with ID " + courseId + " exists.");
        }
        if (assignmentRepository.existsById(id)) {
            throw new IllegalArgumentException("Assignment with ID " + id + " already exists.");
        }

        return assignmentRepository.save(id, title, description, courseId);
    }

    public Collection<Assignment> getAllAssignments() {
        return assignmentRepository.findAll();
    }

    public void deleteAssignment(UserRole role, String assignmentID) {
        if (!isInstructor(role)) {
            throw new IllegalArgumentException("Only Instructors can delete assignments.");
        }

        if (!assignmentRepository.existsById(assignmentID)) {
            throw new IllegalArgumentException("AssignmentID with ID " + assignmentID + " not found.");
        }
        assignmentRepository.delete(assignmentID);
    }

    public void updateAssignment(String id, Assignment updatedAssignment, UserRole role) {
        if (!isInstructor(role)) {
            throw new IllegalArgumentException("Only Instructors can update assignments.");
        }
        if (!courseRepository.existsById(updatedAssignment.getCourseId())) {
            throw new IllegalArgumentException("No course with ID " + updatedAssignment.getCourseId() + " exists.");
        }
        Optional<Assignment> existingAssignment = assignmentRepository.findById(id);
        if (existingAssignment.isEmpty()) {
            throw new IllegalArgumentException("Course with ID " + id + " does not exist.");
        }

        if (assignmentRepository.existsById(updatedAssignment.getId()) && !id.equals(updatedAssignment.getId())) {
            throw new IllegalArgumentException(
                    "Another course with the ID " + updatedAssignment.getId() + " already exists.");
        }

        assignmentRepository.delete(id);
        assignmentRepository.save(updatedAssignment.getId(), updatedAssignment.getTitle(),
                updatedAssignment.getDescription(), updatedAssignment.getCourseId()); // Save the updated course
    }

    public void submit(UserRole role, String assignmentID, String file, String studentID, String courseId) {

        if (!isStudent(role)) {
            throw new IllegalArgumentException("Only students can submit assignments.");
        }
        if (!courseRepository.existsById(courseId)) {
            throw new IllegalArgumentException("No course with ID " + courseId + " exists.");
        }
        if (!assignmentRepository.existsById(assignmentID)) {
            throw new IllegalArgumentException("Assignment with ID " + assignmentID + " not found.");
        }
        assignmentRepository.submit(assignmentID, file, studentID);

    }

    private boolean isInstructor(UserRole role) {
        return role == UserRole.INSTRUCTOR;
    }

    private boolean isStudent(UserRole role) {
        return role == UserRole.STUDENT;
    }
}
