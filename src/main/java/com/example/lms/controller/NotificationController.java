package com.example.lms.controller;

import com.example.lms.model.Notification;
import com.example.lms.model.user_related.User;
import com.example.lms.service.JwtService;
import com.example.lms.service.NotificationService;
import com.example.lms.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;
    private final JwtService jwtService;
    private final UserService userService;


    @GetMapping()
    @PreAuthorize("hasAnyAuthority('INSTRUCTOR', 'ADMIN', 'STUDENT')")
    public List<Notification> getNotificationsByUser(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        String token = authHeader.substring(7);
        String email = jwtService.extractUserName(token);
        User user = userService.getUserByEmail(email);

        return notificationService.getNotificationsByUser(user);
    }
    @GetMapping("/unread")
    @PreAuthorize("hasAnyAuthority('INSTRUCTOR', 'ADMIN', 'STUDENT')")
    public List<Notification> getUnreadNotificationsByUser(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        String token = authHeader.substring(7);
        String email = jwtService.extractUserName(token);
        User user = userService.getUserByEmail(email);

        return notificationService.getUnreadNotificationsByUser(user);
    }
    @PutMapping("/{notificationId}/read")
    @PreAuthorize("hasAnyAuthority('INSTRUCTOR', 'ADMIN', 'STUDENT')")
    public void markAsRead(@PathVariable int notificationId) {
        notificationService.markAsRead(notificationId);
    }

    @PostMapping("/send")
    @PreAuthorize("hasAnyAuthority('INSTRUCTOR')")
    public void sendNotification(@RequestParam("userId") int userId, @RequestBody String message) {
        User user = new User();
        user.setId(userId);
        notificationService.sendNotification(user, message);
    }
//http://localhost:8080/notifications/send?userId=106 && message => string in Body
}