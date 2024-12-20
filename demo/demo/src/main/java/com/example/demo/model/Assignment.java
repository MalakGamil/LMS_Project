package com.example.demo.model;

import java.time.LocalDateTime;

public class Assignment {
    private String id;
    private String title;
    private String description;
    private String courseId; // Links assignment to a course
    private String studentId; // Tracks which student submitted it
    private String fileUrl; // URL for the submitted file (if any)
    private String grade; // Grade assigned by the instructor
    private String feedback; // Feedback from the instructor
    private LocalDateTime dueDate; // The due date for the assignment

    public Assignment() {}

    public Assignment(String id, String title, String description, String courseId, String studentId, String fileUrl, String grade, String feedback, LocalDateTime dueDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.courseId = courseId;
        this.studentId = studentId;
        this.fileUrl = fileUrl;
        this.grade = grade;
        this.feedback = feedback;
        this.dueDate = dueDate;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }
}
