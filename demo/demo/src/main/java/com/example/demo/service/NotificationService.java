package com.example.demo.service;

import com.example.demo.model.Notification;
import com.example.demo.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    // Add a notification for a user
    public void addNotification(String userId, String message) {
        Notification notification = new Notification();
        notification.setId(java.util.UUID.randomUUID().toString()); // Generate unique ID
        notification.setUserId(userId);
        notification.setMessage(message);
        notification.setRead(false);
        notificationRepository.save(notification);
    }

    // Get all notifications for a user
    public List<Notification> getAllNotifications(String userId) {
        return notificationRepository.findByUserId(userId);
    }

    // Get unread notifications for a user
    public List<Notification> getUnreadNotifications(String userId) {
        return notificationRepository.findUnreadByUserId(userId);
    }

    // Mark a notification as read
    public void markNotificationAsRead(String notificationId) {
        notificationRepository.markAsRead(notificationId);
    }
}
