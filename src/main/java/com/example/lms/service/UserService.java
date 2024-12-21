package com.example.lms.service;

import com.example.lms.model.user_related.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {
    UserDetailsService userDetailsService();

    User getUser(int id);
}
