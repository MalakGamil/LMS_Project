package com.example.demo.Model;

import java.util.Arrays;

public class Question {
    private String text;
    private String type; // MCQ, True/False, Short Answer
    private String[] options; // For MCQ only
    private String answer;

    public Question() {
    }

    public Question(String text, String type, String[] options, String answer) {
        this.text = text;
        this.type = type;
        this.options = options;
        this.answer = answer;
    }

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String[] getOptions() { return options; }
    public void setOptions(String[] options) { this.options = options; }

    public String getAnswer() { return answer; }
    public void setAnswer(String answer) { this.answer = answer; }

    @Override
    public String toString() {
        return "Question{" +
                "text='" + text + '\'' +
                ", type='" + type + '\'' +
                ", options=" + Arrays.toString(options) +
                ", answer='" + answer + '\'' +
                '}';
    }
}