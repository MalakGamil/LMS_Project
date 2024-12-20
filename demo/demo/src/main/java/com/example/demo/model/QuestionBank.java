package com.example.demo.model;

import java.util.List;

public class QuestionBank {
    private String courseId;
    private List<Question> questions;

    public QuestionBank(String courseId, List<Question> questions) {
        this.courseId = courseId;
        this.questions = questions;
    }

    // Getters and setters
    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
