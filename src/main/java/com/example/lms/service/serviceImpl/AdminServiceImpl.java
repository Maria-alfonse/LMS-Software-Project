package com.example.lms.service.serviceImpl;

import com.example.lms.model.user_related.Admin;
import com.example.lms.repository.AdminRepo;
import com.example.lms.service.AdminService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional

public class AdminServiceImpl implements AdminService {

    private final AdminRepo adminRepo;

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
