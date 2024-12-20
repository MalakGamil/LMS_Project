package com.example.demo.model;

public class Notification {
    private String id;         // Unique identifier for the notification
    private String userId;     // The user to whom the notification belongs
    private String message;    // The content of the notification
    private boolean read;      // Whether the notification has been read

    // Default constructor
    public Notification() {
    }

    // Parameterized constructor
    public Notification(String id, String userId, String message, boolean read) {
        this.id = id;
        this.userId = userId;
        this.message = message;
        this.read = read;
    }

    // Getter and setter methods
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    // toString method for debugging purposes
    @Override
    public String toString() {
        return "Notification{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", message='" + message + '\'' +
                ", read=" + read +
                '}';
    }
}
