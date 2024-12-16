package com.example.project.service;

import com.example.project.model.Admin;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final Map<Integer, Admin> admins = new HashMap<>();

    private final UserService userService;

    @Override
    public Boolean addAdmin(Admin admin) {
        if (admins.containsKey(admin.getId()))
            return false;
        admins.put(admin.getId(), admin);
        userService.addUser(admin);
        return true;
    }

    @Override
    public Boolean deleteAdmin(int id) {
        if (admins.containsKey(id)) {
            admins.remove(id);
            return true;
        }
        return false;
    }

    @Override
    public Admin getAdmin(int id) {
        return admins.get(id);
    }
}
