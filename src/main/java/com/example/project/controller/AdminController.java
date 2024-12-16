package com.example.project.controller;

import com.example.project.model.Users.Admin;
import com.example.project.model.Users.Instructor;
import com.example.project.model.Users.Student;
import com.example.project.service.AdminService;
import com.example.project.service.StudentService;
import com.example.project.service.InstructorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;
    private final StudentService studentService;
    private final InstructorService instructorService;

    @PostMapping("/create/admin")
    public boolean createAdmin(@RequestBody Admin admin) {
        return adminService.addAdmin(admin);
    }

    @PostMapping("/create/student")
    public boolean createStudent(@RequestBody Student student){
        return studentService.addStudent(student);
    }

    @PostMapping("/create/instructor")
    public boolean createInstructor(@RequestBody Instructor instructor){
        return instructorService.addInstructor(instructor);
    }
}
