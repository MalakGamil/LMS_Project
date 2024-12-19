package com.example.demo.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Quiz {
    private String title;
    private String id;
    private String courseId;
    private List<Question> questions;

    public Quiz() {
        this.questions = new ArrayList<>();
    }

    public Quiz(String id, String title, String courseId) {
        this.id = id;
        this.title = title;
        this.courseId = courseId;
        this.questions = new ArrayList<>();
    }
    public void selectRandomQuestions(List<Question> questionBank, int numberOfQuestions) {
        Collections.shuffle(questionBank); // Randomize the list
        this.questions = questionBank.subList(0, Math.min(numberOfQuestions, questionBank.size()));
    }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getCourseId() { return courseId; }
    public void setCourseId(String courseId) { this.courseId = courseId; }

    public List<Question> getQuestions() { return questions; }
    public void setQuestions(List<Question> questions) { this.questions = questions; }

    public void addQuestion(Question question) {
        this.questions.add(question);
    }
}