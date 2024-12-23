package com.example.lms.controller;

import com.example.lms.model.user_related.Admin;
import com.example.lms.model.user_related.Role;
import com.example.lms.model.user_related.User;
import com.example.lms.service.AdminService;
import com.example.lms.service.InstructorService;
import com.example.lms.service.JwtService;
import com.example.lms.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;
    private final JwtService jwtService;
    private final UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/user/{id}/role")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> setUserRole(@PathVariable("id") int id, @RequestBody Map<String, String> request){
        String role = request.get("role");
        System.out.println("The role is: " + role);
        User user = adminService.setUserRole(id, role.toUpperCase());
        return ResponseEntity.ok(user);
    }

    @PostMapping("/add")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public Admin addAdmin(@RequestBody Admin admin) {
        String encodedPassword = passwordEncoder.encode(admin.getPassword());
        admin.setPassword(encodedPassword);
        admin.setRole(Role.ADMIN);
        return adminService.addAdmin(admin);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public void deleteAdmin(@PathVariable Integer id) {

        adminService.deleteAdmin(id);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("/get/{id}")
    public Admin getAdmin(@PathVariable Integer id) {

        return adminService.getAdmin(id);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("/get_me")
    public Admin getAdmin(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        String token = authHeader.substring(7);
        String email = jwtService.extractUserName(token);
        User user = userService.getUserByEmail(email);
        if (user == null)
            return null;
        Integer id = user.getId();
        return adminService.getAdmin(id);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping
    public List<Admin> getAllAdmin() {
        return adminService.getAll();
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PatchMapping("/update_me")
    public void updateAdmin(@RequestBody Admin admin, HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        String token = authHeader.substring(7);
        String email = jwtService.extractUserName(token);
        User user = userService.getUserByEmail(email);
        if (user == null)
            return;
        String encodedPassword = passwordEncoder.encode(admin.getPassword());
        admin.setPassword(encodedPassword);
        Integer id = user.getId();
        adminService.updateAdmin(id,admin);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PatchMapping("/update/{id}")
    public void updateAdmin(@PathVariable Integer id ,@RequestBody Admin admin, HttpServletRequest request) {
        String encodedPassword = passwordEncoder.encode(admin.getPassword());
        admin.setPassword(encodedPassword);
        adminService.updateAdmin(id,admin);
    }
}
