package com.example.demo.model;

import java.util.HashSet;
import java.util.Set;

public class Lesson {
    private String lessonId;                // Unique identifier for the lesson
    private String lessonTitle;             // Title of the lesson
    private String lessonContent;           // Content of the lesson
    private String otp;                     // OTP for attendance verification
    private Long otpGeneratedTime;          // Time when OTP was generated (nullable)
    private Set<String> attendedStudents;   // Set of students who attended the lesson
    private Set<String> otpAttempts;        // Set of students who attempted to enter OTP

    // Default constructor
    public Lesson() {
        this.attendedStudents = new HashSet<>();
        this.otpAttempts = new HashSet<>();
    }

    // Parameterized constructor
    public Lesson(String lessonId, String lessonTitle, String lessonContent) {
        this.lessonId = lessonId;
        this.lessonTitle = lessonTitle;
        this.lessonContent = lessonContent;
        this.attendedStudents = new HashSet<>();
        this.otpAttempts = new HashSet<>();
    }

    // Getters and Setters
    public String getLessonId() {
        return lessonId;
    }

    public void setLessonId(String lessonId) {
        this.lessonId = lessonId;
    }

    public String getLessonTitle() {
        return lessonTitle;
    }

    public void setLessonTitle(String lessonTitle) {
        this.lessonTitle = lessonTitle;
    }

    public String getLessonContent() {
        return lessonContent;
    }

    public void setLessonContent(String lessonContent) {
        this.lessonContent = lessonContent;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public Long getOtpGeneratedTime() {
        return otpGeneratedTime;
    }

    public void setOtpGeneratedTime(long otpGeneratedTime) {
        this.otpGeneratedTime = otpGeneratedTime;
    }

    public Set<String> getAttendedStudents() {
        return attendedStudents;
    }

    public void setAttendedStudents(Set<String> attendedStudents) {
        this.attendedStudents = attendedStudents;
    }

    public Set<String> getOtpAttempts() {
        return otpAttempts;
    }

    public void setOtpAttempts(Set<String> otpAttempts) {
        this.otpAttempts = otpAttempts;
    }

    // Mark a student as attended
    public void markAttendance(String studentId) {
        this.attendedStudents.add(studentId);
    }

    // Add an OTP attempt for a student
    public void addOtpAttempt(String studentId) {
        this.otpAttempts.add(studentId);
    }

    // Check if OTP has expired (e.g., 10 minutes after generation)
    public boolean isOtpExpired() {
        if (otpGeneratedTime == null) return true; // Treat null as expired
        long currentTime = System.currentTimeMillis();
        return currentTime - otpGeneratedTime > 600000; // 10 minutes in milliseconds
    }

    // Check if a student has exceeded the OTP attempts (e.g., max 3 attempts)
    public boolean hasExceededOtpAttempts(String studentId) {
        int maxAttempts = 3;
        long studentAttempts = otpAttempts.stream().filter(id -> id.equals(studentId)).count();
        return studentAttempts >= maxAttempts;
    }

    // Method to get lessons attended by a specific student
    public boolean isStudentAttended(String studentId) {
        return attendedStudents.contains(studentId);
    }

}
