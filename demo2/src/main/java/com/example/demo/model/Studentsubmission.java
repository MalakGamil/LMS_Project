package com.example.demo.model;


public class Studentsubmission {
    private String studentid;
    private String grade;
    private String feedback;

    public Studentsubmission(String studentid) {
        this.studentid = studentid;

    }

    public String getStudentid() {
        return this.studentid;
    }

    public void setStudentid(String studentid) {
        this.studentid = studentid;
    }

    public String getGrade() {
        return this.grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
    public String getFeedback() {
        return this.feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

}
