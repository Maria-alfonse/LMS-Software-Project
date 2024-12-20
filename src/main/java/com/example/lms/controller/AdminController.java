package com.example.lms.controller;

import com.example.lms.model.user_related.Admin;
import com.example.lms.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;

    @PostMapping("/add")
    public Admin addAdmin(@RequestBody Admin admin) {
        return adminService.addAdmin(admin);
    }

    @PostMapping("/delete/{id}")
    public void deleteAdmin(@PathVariable Integer id) {
        adminService.deleteAdmin(id);
    }

    @GetMapping("/get/{id}")
    public Admin getAdmin(@PathVariable Integer id) {
        return adminService.getAdmin(id);
    }

    @GetMapping
    public List<Admin> getAllAdmin() {
        return adminService.getAll();
    }

    @PostMapping("/update/{id}")
    public void updateAdmin(@PathVariable Integer id, @RequestBody Admin admin) {
        adminService.updateAdmin(id,admin);
    }




}
