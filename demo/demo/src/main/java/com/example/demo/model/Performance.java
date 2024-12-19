package com.example.demo.model;

public class Performance {
    private String userId;           // The user to whom the performance belongs
    private String courseId;         // The course for which performance is tracked
    private int attendancePercentage; // Attendance percentage for the course
    private int score;               // Score achieved in the course

    // Default constructor
    public Performance() {
    }

    // Parameterized constructor
    public Performance(String userId, String courseId, int attendancePercentage, int score) {
        this.userId = userId;
        this.courseId = courseId;
        this.attendancePercentage = attendancePercentage;
        this.score = score;
    }

    // Getter and setter methods
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public int getAttendancePercentage() {
        return attendancePercentage;
    }

    public void setAttendancePercentage(int attendancePercentage) {
        this.attendancePercentage = attendancePercentage;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    // toString method for debugging purposes
    @Override
    public String toString() {
        return "Performance{" +
                "userId='" + userId + '\'' +
                ", courseId='" + courseId + '\'' +
                ", attendancePercentage=" + attendancePercentage +
                ", score=" + score +
                '}';
    }
}
