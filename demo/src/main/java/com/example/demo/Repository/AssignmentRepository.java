package com.example.demo.Repository;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.example.demo.Model.Assignment;
import com.example.demo.Model.studentID_file;

@Repository
public class AssignmentRepository {
    private final Map<String, Assignment> assignments = new HashMap<>();

    public Assignment save(String id, String title, String description, String courseId) {
        Assignment assignment = new Assignment( id, title,  description, courseId);
        assignments.put(assignment.getId(),assignment);
        return assignment;
    }
    public void delete(String assignmentID) {
        assignments.remove(assignmentID);
    }

    public boolean existsById(String assignmentID) {
        return assignments.containsKey(assignmentID);
    }

    public Collection<Assignment> findAll() {
        return assignments.values();
    }
    public void submit(String assignmentID, String file, String studentId) {
        Assignment assignment = assignments.get(assignmentID);
        if (assignment == null) {
            throw new IllegalArgumentException("Assignment with ID " + assignmentID + " not found.");
        }
        studentID_file student = new studentID_file(file, studentId);
        assignment.addStudent(student);
    }
    

    public Optional<Assignment> findById(String id) {
        return Optional.ofNullable(assignments.get(id));
    }
}
