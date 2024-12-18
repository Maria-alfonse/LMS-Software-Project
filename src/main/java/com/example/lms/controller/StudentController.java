package com.example.lms.controller;

import com.example.lms.model.user_related.Student;
import com.example.lms.model.user_related.UserData;
import com.example.lms.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @PostMapping("/student/add")
    public UserData addStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
    }

}
