package com.example.demo.model;

import java.util.List;

public class Question {
    private String id;
    private String text; // Question text
    private String type; // Question type: MCQ, True/False, Short Answer
    private List<String> options; // Options for MCQs or True/False
    private String correctAnswer; // Correct answer for MCQs, True/False, or Short Answer

    public Question(String id, String text, String type, List<String> options, String correctAnswer) {
        this.id = id;
        this.text = text;
        this.type = type;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
}
