package com.example.demo.repository;

import com.example.demo.model.Assignment;
import com.example.demo.model.Studentsubmission;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class AssignmentRepository {
    private final Map<String, Assignment> assignments = new HashMap<>();

    // Save an assignment to the repository
    public void save(Assignment assignment) {
        assignments.put(assignment.getId(), assignment);
    }

    // Find an assignment by its ID
    public Optional<Assignment> findById(String id) {
        return Optional.ofNullable(assignments.get(id));
    }

    // Find assignments by the course ID
    public List<Assignment> findByCourseId(String courseId) {
        List<Assignment> result = new ArrayList<>();
        for (Assignment assignment : assignments.values()) {
            if (assignment.getCourseId().equals(courseId)) {
                result.add(assignment);
            }
        }
        return result;
    }

    // Get all assignments for a student
    public List<Assignment> findByStudentId(String studentId) {
        List<Assignment> result = new ArrayList<>();
        for (Assignment assignment : assignments.values()) {
            for (Studentsubmission student : assignment.getStudents()) {
                if (student.getStudentid().equals(studentId)) {
                        result.add(assignment);
                }
            }
        }
        return result;
    }

    // Delete an assignment by ID
    public void deleteById(String id) {
        assignments.remove(id);
    }

    // Update an existing assignment by ID
    public void update(Assignment assignment) {
        if (assignments.containsKey(assignment.getId())) {
            assignments.put(assignment.getId(), assignment);
        }
    }
}
