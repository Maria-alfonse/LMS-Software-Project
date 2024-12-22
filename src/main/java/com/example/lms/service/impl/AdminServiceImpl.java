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

import java.util.List;
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


    @Override
    public Admin addAdmin( Admin admin) {
        if (adminRepo.findByEmail(admin.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Admin with the same email already exists");
        }
        return adminRepo.save(admin);
    }

    @Override
    public void deleteAdmin(Integer id) {
        Admin admin = adminRepo.findById((int) id)
                .orElseThrow(() -> new IllegalArgumentException("Admin with the given ID does not exist."));
        adminRepo.delete(admin);
    }


    @Override
    public void updateAdmin(Integer id,Admin updatedAdmin) {
//        Optional<Admin> existingAdmin = adminRepo.findByEmail(updatedAdmin.getEmail());
//        if(existingAdmin.isPresent()) {
//            throw new IllegalArgumentException("Admin with email"+email+" already exists");
//        }

//        if(!Objects.equals(updatedAdmin.getEmail(), email)) {
//            Optional<Admin> admin = adminRepo.findByEmail(updatedAdmin.getEmail());
//            if(admin.isPresent()) {
//                throw new IllegalArgumentException("Admin with email"+email+" already exists");
//            }
//        }
        Admin existingAdmin = adminRepo.findById((int) id)
                .orElseThrow(() -> new IllegalArgumentException("Admin with Id  " + id + " does not exist."));

        if (updatedAdmin.getEmail() != null)
            existingAdmin.setEmail(updatedAdmin.getEmail());

        if (updatedAdmin.getName() != null)
            existingAdmin.setName(updatedAdmin.getName());

        if (updatedAdmin.getPassword() != null)
            existingAdmin.setPassword(updatedAdmin.getPassword());

        adminRepo.save(existingAdmin);
    }

    @Override
    public Admin getAdmin(Integer id){
//        Optional<Admin>admin=adminRepo.findById((int) id);
//        return admin.orElse(null);
        return adminRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Admin with the given ID does not exist."));

    }


    @Override
    public List<Admin> getAll(){
        return adminRepo.findAll();
    }
}
