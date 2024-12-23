package com.example.lms.controller;
import com.example.lms.model.user_related.Instructor;
import com.example.lms.model.user_related.Role;
import com.example.lms.model.user_related.User;
import com.example.lms.service.InstructorService;
import com.example.lms.service.JwtService;
import com.example.lms.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/instructor")
public class InstructorController {
    private final JwtService jwtService;
    private final UserService userService;
    private final InstructorService instructorService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping("/add")
    public Instructor addInstructor(@RequestBody Instructor instructor) {
        String encodedPassword = passwordEncoder.encode(instructor.getPassword());
        instructor.setPassword(encodedPassword);
        instructor.setRole(Role.INSTRUCTOR);
        return instructorService.addInstructor(instructor);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public void deleteInstructor(@PathVariable Integer id) {
        instructorService.deleteInstructor(id);
    }


    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PatchMapping("/update/{id}")
    public void updateInstructor(@PathVariable Integer id, @RequestBody Instructor instructor) {
        String encodedPassword = passwordEncoder.encode(instructor.getPassword());
        instructor.setPassword(encodedPassword);
        instructorService.updateInstructor(id, instructor);
    }

    @PreAuthorize("hasAnyAuthority('INSTRUCTOR')")
    @PatchMapping("/update_me")
    public void updateInstructor(@RequestBody Instructor instructor, HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        String token = authHeader.substring(7);
        String email = jwtService.extractUserName(token);
        User user = userService.getUserByEmail(email);
        if (user == null)
            return;
        String encodedPassword = passwordEncoder.encode(instructor.getPassword());
        instructor.setPassword(encodedPassword);
        Integer id = user.getId();
        instructorService.updateInstructor(id, instructor);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("/get/{id}")
    public Instructor getInstructor(@PathVariable Integer id) {
        return instructorService.getInstructor(id);
    }

    @PreAuthorize("hasAnyAuthority('INSTRUCTOR')")
    @GetMapping("/get_me")
    public Instructor getInstructor(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        String token = authHeader.substring(7);
        String email = jwtService.extractUserName(token);
        User user = userService.getUserByEmail(email);
        if (user == null)
            return null;
        Integer id = user.getId();
        return instructorService.getInstructor(id);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping
    public List<Instructor> getInstructors() {
        return instructorService.getAll();
    }
}
