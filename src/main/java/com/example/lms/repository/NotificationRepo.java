package com.example.lms.repository;

import com.example.lms.model.notifications.Notification;
import com.example.lms.model.user_related.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepo extends JpaRepository<Notification, Integer> {
    List<Notification> findByUserAndIsRead(User user, boolean isRead);
    List<Notification> findByUser(User user);
}