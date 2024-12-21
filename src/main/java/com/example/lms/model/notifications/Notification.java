package com.example.lms.model.notifications;

import com.example.lms.model.user_related.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String message;

    private boolean isRead;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
