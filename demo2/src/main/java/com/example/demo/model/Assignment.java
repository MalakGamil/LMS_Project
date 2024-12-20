package com.example.demo.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Assignment {
    private String id;
    private String title;
    private String description;
    private String courseId; // Links assignment to a course
    private List<Studentsubmission> students = new ArrayList<>(); // Tracks which student submitted it
    private String fileUrl; // URL for the submitted file (if any)
    private LocalDateTime dueDate; // The due date for the assignment

    public Assignment() {
    }

    public Assignment(String id, String title, String description, String courseId, String fileUrl,
            LocalDateTime dueDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.courseId = courseId;
        this.students = new ArrayList<>();
        this.fileUrl = fileUrl;
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

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public List<Studentsubmission> getStudents() {
        return this.students;
    }

    public void setStudents(List<Studentsubmission> students) {
        this.students = students;
    }
}
