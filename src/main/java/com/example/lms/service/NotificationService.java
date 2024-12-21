package com.example.lms.service;

import com.example.lms.model.notifications.Notification;
import com.example.lms.model.user_related.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface NotificationService {
    Notification createNotification(String message, User user);
    List<Notification> getUnreadNotifications(User user);
    List<Notification> getAllNotifications(User user);
    void markAsRead(int notificationId);
}