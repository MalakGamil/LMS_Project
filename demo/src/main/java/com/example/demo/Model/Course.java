package com.example.demo.Model;

import java.util.ArrayList;
import java.util.List;

public class Course {
    private String id;
    private String title;
    private String description;
    private Integer hours;
    private String instructorId;
    private List<Question> questionBank;

    public Course() {
    }

    public Course(String id, String title, String description, Integer hours, String instructor) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.hours = hours;
        this.instructorId = instructor;
        this.questionBank = new ArrayList<>();
    }

    public Course(String id, String title,String description, Integer hours ) {
        this.id = id;
        this.description = description;
        this.hours = hours;
        this.title = title;
    }

    public List<Question> getQuestionBank() {
        return questionBank;
    }

    public void addQuestionToBank(Question question) {
        this.questionBank.add(question);
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

    public Integer getHours() {
        return hours;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }

    public String getInstructorID() {
        return instructorId;
    }

    public void setInstructorID(String instructor) {
        this.instructorId = instructor;
    }
}
