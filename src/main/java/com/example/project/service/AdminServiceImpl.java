package com.example.project.service;

import com.example.project.model.Users.Admin;
import com.example.project.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final AdminRepository adminRepo;
    private final UserService userService;

    @Override
    public Boolean addAdmin(Admin admin) {
        Admin newAdmin = adminRepo.save(admin);
        return true;
    }

//    @Override
//    public Boolean deleteAdmin(int id) {
//        if (admins.containsKey(id)) {
//            admins.remove(id);
//            return true;
//        }
//        return false;
//    }

    @Override
    public Admin getAdmin(int id) {
        Optional<Admin> admin = adminRepo.findById(id);
        if(admin.isPresent())
            return admin.get();
        return null;
    }
}
