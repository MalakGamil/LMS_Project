package com.example.demo.Model;

public class SubmitAssignmentRequest {
    private String assignmentId;
    private studentID_file student;
    private String courseId;

    public SubmitAssignmentRequest(String assignmentId, studentID_file student,String courseId) {
        this.assignmentId = assignmentId;
        this.student = student;
        this.courseId=courseId;
    }

    public String getassignmentId() {
        return this.assignmentId;
    }

    public void setassignmentId(String assignmentId) {
        this.assignmentId = assignmentId;
    }

    public studentID_file getStudent() {
        return this.student;
    }

    public void setStudent(studentID_file studentId) {
        this.student = studentId;
    }

    public String getCourseId() {
        return this.courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }
}