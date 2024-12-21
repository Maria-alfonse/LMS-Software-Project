package com.example.lms.service;

import com.example.lms.model.user_related.Role;
import com.example.lms.model.user_related.User;

public interface AdminService {
    User setUserRole(int userId, String roleName);
}
