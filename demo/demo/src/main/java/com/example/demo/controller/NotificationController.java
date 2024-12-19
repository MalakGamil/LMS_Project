package com.example.demo.controller;

import com.example.demo.model.Notification;
import com.example.demo.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/unread")
    public List<Notification> getUnreadNotifications(@RequestParam String userId) {
        return notificationService.getUnreadNotifications(userId);
    }

    @GetMapping
    public List<Notification> getAllNotifications(@RequestParam String userId) {
        return notificationService.getAllNotifications(userId);
    }
}