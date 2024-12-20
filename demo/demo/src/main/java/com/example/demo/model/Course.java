package com.example.demo.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Course {
    private String id;                     // Unique identifier for the course
    private String title;                  // Course title
    private String description;            // Course description
    private String duration;               // Course duration (e.g., "3 weeks")
    private List<Lesson> lessons;          // List of lessons in this course
    private List<String> enrolledStudents; // List of enrolled student IDs
    private List<String> mediaFiles;       // List of media file URLs for course content

    // Default constructor
    public Course() {
        this.lessons = new ArrayList<>();
        this.enrolledStudents = new ArrayList<>();
        this.mediaFiles = new ArrayList<>();
    }

    // Parameterized constructor
    public Course(String id, String title, String description, String duration, List<Lesson> lessons) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.duration = duration;
        this.lessons = lessons;
        this.enrolledStudents = new ArrayList<>();
        this.mediaFiles = new ArrayList<>();
    }

    // Getter and Setter methods
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

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }

    public List<String> getEnrolledStudents() {
        return enrolledStudents;
    }

    public void setEnrolledStudents(List<String> enrolledStudents) {
        this.enrolledStudents = enrolledStudents;
    }

    public List<String> getMediaFiles() {
        return mediaFiles;
    }

    public void setMediaFiles(List<String> mediaFiles) {
        this.mediaFiles = mediaFiles;
    }

    // Add a student to the enrolled students list
    public void addStudent(String studentId) {
        this.enrolledStudents.add(studentId);
    }

    // Add a media file to the course's media list
    public void addMediaFile(String mediaUrl) {
        this.mediaFiles.add(mediaUrl);
    }

    // Add a lesson to the course's lesson list
    public void addLesson(Lesson lesson) {
        this.lessons.add(lesson);
    }

    // toString method for debugging purposes
    @Override
    public String toString() {
        return "Course{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", duration='" + duration + '\'' +
                ", lessons=" + lessons +
                ", enrolledStudents=" + enrolledStudents +
                ", mediaFiles=" + mediaFiles +
                '}';
    }
}


