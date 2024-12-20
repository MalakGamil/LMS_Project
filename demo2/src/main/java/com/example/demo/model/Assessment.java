package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

public class Assessment {
    private String id;          // Unique identifier for the assessment
    private String courseId;    // ID of the course this assessment belongs to
    private String type;        // Assessment type (e.g., "quiz" or "assignment")
    private List<String> questions;   // List of questions
    private List<String> submissions; // List of student submissions

    // Default constructor
    public Assessment() {
        this.questions = new ArrayList<>();
        this.submissions = new ArrayList<>();
    }

    // Parameterized constructor
    public Assessment(String id, String courseId, String type) {
        this.id = id;
        this.courseId = courseId;
        this.type = type;
        this.questions = new ArrayList<>();
        this.submissions = new ArrayList<>();
    }

    // Getter for id
    public String getId() {
        return id;
    }

    // Setter for id
    public void setId(String id) {
        this.id = id;
    }

    // Getter for courseId
    public String getCourseId() {
        return courseId;
    }

    // Setter for courseId
    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    // Getter for type
    public String getType() {
        return type;
    }

    // Setter for type
    public void setType(String type) {
        this.type = type;
    }

    // Getter for questions
    public List<String> getQuestions() {
        return questions;
    }

    // Setter for questions
    public void setQuestions(List<String> questions) {
        this.questions = questions;
    }

    // Method to add a question
    public void addQuestion(String question) {
        this.questions.add(question);
    }

    // Getter for submissions
    public List<String> getSubmissions() {
        return submissions;
    }

    // Setter for submissions
    public void setSubmissions(List<String> submissions) {
        this.submissions = submissions;
    }

    // Method to add a submission
    public void addSubmission(String submission) {
        this.submissions.add(submission);
    }

    // toString method for debugging purposes
    @Override
    public String toString() {
        return "Assessment{" +
                "id='" + id + '\'' +
                ", courseId='" + courseId + '\'' +
                ", type='" + type + '\'' +
                ", questions=" + questions +
                ", submissions=" + submissions +
                '}';
    }
}
