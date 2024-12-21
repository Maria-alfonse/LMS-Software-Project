package com.example.lms.service.impl;

import com.example.lms.model.user_related.*;
import com.example.lms.repository.AdminRepo;
import com.example.lms.repository.InstructorRepo;
import com.example.lms.repository.StudentRepo;
import com.example.lms.repository.UserRepo;
import com.example.lms.service.AdminService;
import com.example.lms.service.StudentService;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final UserRepo userRepo;

    private final AdminRepo adminRepo;

    private final StudentRepo studentRepo;

    private final InstructorRepo instructorRepo;

    private final EntityManager entityManager;

    @Override
    public User setUserRole(int userId, String roleName){
        User user = userRepo.findById(userId).orElse(null);
        Role role = Role.valueOf(roleName.toUpperCase());
        System.out.println("The internal role is: " + role.name());
        if (user != null) {
            User newUser = switch (role) {
                case ADMIN -> new Admin();
                case INSTRUCTOR -> new Instructor();
                case STUDENT -> new Student();
                default -> throw new IllegalArgumentException("Unsupported role: " + roleName);
            };

            newUser.setName(user.getName());
            newUser.setEmail(user.getEmail());
            newUser.setPassword(user.getPassword());
            newUser.setRole(role);

            userRepo.delete(user);
            return userRepo.save(newUser);
        }
        return null;
    }
}
