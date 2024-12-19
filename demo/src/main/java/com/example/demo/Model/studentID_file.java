package com.example.demo.Model;

public class studentID_file{
    private String submittedFile;
    private String studentId ;

    public studentID_file(String submittedFile, String studentId) {
        this.submittedFile = submittedFile;
        this.studentId = studentId;
    }

    public String getSubmittedFile() {
        return this.submittedFile;
    }

    public void setSubmittedFile(String submittedFile) {
        this.submittedFile = submittedFile;
    }

    public String getStudentId() {
        return this.studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

}
