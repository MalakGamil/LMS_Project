package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

public class Course {
    private String id;                     // Unique identifier for the course
    private String title;                  // Course title
    private String description;            // Course description
    private List<String> enrolledStudents; // List of enrolled student IDs

    // Default constructor
    public Course() {
        this.enrolledStudents = new ArrayList<>();
    }

    // Parameterized constructor
    public Course(String id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.enrolledStudents = new ArrayList<>();
    }

    // Getter for id
    public String getId() {
        return id;
    }

    // Setter for id
    public void setId(String id) {
        this.id = id;
    }

    // Getter for title
    public String getTitle() {
        return title;
    }

    // Setter for title
    public void setTitle(String title) {
        this.title = title;
    }

    // Getter for description
    public String getDescription() {
        return description;
    }

    // Setter for description
    public void setDescription(String description) {
        this.description = description;
    }

    // Getter for enrolledStudents
    public List<String> getEnrolledStudents() {
        return enrolledStudents;
    }

    // Setter for enrolledStudents
    public void setEnrolledStudents(List<String> enrolledStudents) {
        this.enrolledStudents = enrolledStudents;
    }

    // Method to add a student to the enrolled students list
    public void addStudent(String studentId) {
        this.enrolledStudents.add(studentId);
    }

    // toString method for debugging purposes
    @Override
    public String toString() {
        return "Course{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", enrolledStudents=" + enrolledStudents +
                '}';
    }
}
