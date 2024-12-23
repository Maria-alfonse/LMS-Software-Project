package com.example.lms.controller;

import com.example.lms.model.user_related.*;
import com.example.lms.service.InstructorService;
import com.example.lms.service.JwtService;
import com.example.lms.service.StudentService;
import com.example.lms.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;
    private final JwtService jwtService;
    private final UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping("/add")
    public Student addStudent(@RequestBody Student student) {
        String encodedPassword = passwordEncoder.encode(student.getPassword());
        student.setPassword(encodedPassword);
        student.setRole(Role.STUDENT);
        return studentService.addStudent(student);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public void deleteStudent(@PathVariable Integer id) {
        studentService.deleteStudent(id);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PatchMapping("/update/{id}")
    public void updateStudent(@PathVariable Integer id, @RequestBody Student student) {
        String encodedPassword = passwordEncoder.encode(student.getPassword());
            student.setPassword(encodedPassword);
        studentService.updateStudent(id, student);
    }

    @PreAuthorize("hasAnyAuthority('STUDENT')")
    @PatchMapping("/update_me")
    public void updateStudent(@RequestBody Student student, HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        String token = authHeader.substring(7);
        String email = jwtService.extractUserName(token);
        User user = userService.getUserByEmail(email);
        if (user == null)
            return;
        String encodedPassword = passwordEncoder.encode(student.getPassword());
        student.setPassword(encodedPassword);
        Integer id = user.getId();
        studentService.updateStudent(id, student);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("/get/{id}")
    public Student getStudent(@PathVariable Integer id) {
        return studentService.getStudent(id);
    }

    @PreAuthorize("hasAnyAuthority('STUDENT')")
    @GetMapping("/get_me")
    public Student getStudent(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        String token = authHeader.substring(7);
        String email = jwtService.extractUserName(token);
        User user = userService.getUserByEmail(email);
        if (user == null)
            return null;
        Integer id = user.getId();
        return studentService.getStudent(id);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAll();
    }

}