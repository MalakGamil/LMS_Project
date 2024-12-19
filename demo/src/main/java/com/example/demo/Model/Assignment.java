package com.example.demo.Model;

import java.util.ArrayList;
import java.util.List;

public class Assignment {
    private String id ;
    private String title;
    private String description;
    private String courseId;
    private List<studentID_file> students;

    public Assignment() {
    }

    public Assignment(String id, String title, String description, String courseId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.courseId = courseId;
        this.students = new ArrayList<>();
    }



    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getCourseId() {
        return this.courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public List<studentID_file> getStudents() {
        return this.students;
    }

    public void addStudent(studentID_file student) {
        this.students.add(student);
    }  
}
