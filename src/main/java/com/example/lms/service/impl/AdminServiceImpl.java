package com.example.lms.service.impl;

import com.example.lms.model.user_related.Role;
import com.example.lms.model.user_related.User;
import com.example.lms.repository.AdminRepo;
import com.example.lms.repository.UserRepo;
import com.example.lms.service.AdminService;
import com.example.lms.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final AdminRepo adminRepo;
    private final UserRepo userRepo;

    @Override
    public User setUserRole(int userId, String roleName){
        Optional<User> user = userRepo.findById(userId);
        Role role = Role.valueOf(roleName.toUpperCase());
        System.out.println("The internal role is: " + role.name());
        if(user.isPresent()){
            user.get().setRole(role);
            userRepo.save(user.get());
        }
        return null;
    }
}
