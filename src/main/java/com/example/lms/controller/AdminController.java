package com.example.lms.controller;

import com.example.lms.model.user_related.User;
import com.example.lms.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;

    @PostMapping("/user/{id}/role")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> setUserRole(@PathVariable("id") int id, @RequestBody Map<String, String> request){
        String role = request.get("role");
        System.out.println("The role is: " + role);
        User user = adminService.setUserRole(id, role.toUpperCase());
        return ResponseEntity.ok(user);
    }
}
