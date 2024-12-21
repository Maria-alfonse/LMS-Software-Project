package com.example.lms.service;

import com.example.lms.model.notifications.Notification;
import com.example.lms.model.user_related.User;
import com.example.lms.repository.NotificationRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {
    private final NotificationRepo notificationRepo;

    @Override
    public Notification createNotification(String message, User user) {
        Notification notification = new Notification();
        notification.setMessage(message);
        notification.setUser(user);
        notification.setRead(false);
        return notificationRepo.save(notification);
    }

    @Override
    public List<Notification> getUnreadNotifications(User user) {
        return notificationRepo.findByUserAndIsRead(user, false);
    }

    @Override
    public List<Notification> getAllNotifications(User user) {
        return notificationRepo.findByUser(user);
    }

    @Override
    public void markAsRead(int notificationId) {
        Notification notification = notificationRepo.findById(notificationId).orElse(null);
        if (notification != null) {
            notification.setRead(true);
            notificationRepo.save(notification);
        }
    }
}