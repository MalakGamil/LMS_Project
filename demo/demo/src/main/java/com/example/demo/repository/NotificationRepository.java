package com.example.demo.repository;

import com.example.demo.model.Notification;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
public class NotificationRepository {

    // In-memory storage for notifications
    private final Map<String, Notification> notificationStorage = new ConcurrentHashMap<>();

    // Save a notification
    public void save(Notification notification) {
        notificationStorage.put(notification.getId(), notification);
    }

    // Find a notification by its ID
    public Notification findById(String id) {
        return notificationStorage.get(id);
    }

    // Find all notifications for a specific user
    public List<Notification> findByUserId(String userId) {
        return notificationStorage.values().stream()
                .filter(notification -> notification.getUserId().equals(userId))
                .collect(Collectors.toList());
    }

    // Find all unread notifications for a specific user
    public List<Notification> findUnreadByUserId(String userId) {
        return notificationStorage.values().stream()
                .filter(notification -> notification.getUserId().equals(userId) && !notification.isRead())
                .collect(Collectors.toList());
    }

    // Mark a notification as read
    public void markAsRead(String id) {
        Notification notification = findById(id);
        if (notification != null) {
            notification.setRead(true);
        }
    }
}
