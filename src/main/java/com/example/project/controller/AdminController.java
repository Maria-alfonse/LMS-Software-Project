package com.example.project.controller;

import com.example.project.model.Users.Admin;
import com.example.project.model.Users.Instructor;
import com.example.project.model.Users.Student;
import com.example.project.service.AdminService;
import com.example.project.service.Course.CourseService;
import com.example.project.service.StudentService;
import com.example.project.service.InstructorService;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;
//    private final StudentService studentService;
//    private final InstructorService instructorService;
//    private final CourseService courseService;

    @PostMapping("/admin/create")
    public boolean createAdmin(@RequestBody Admin admin) {
        return adminService.addAdmin(admin);
    }
//
//    @PostMapping("/student/create")
//    public boolean createStudent(@RequestBody Student student){
//        return studentService.addStudent(student);
//    }
//
//    @PostMapping("/instructor/create")
//    public boolean createInstructor(@RequestBody Instructor instructor){
//        return instructorService.addInstructor(instructor);
    }
}
