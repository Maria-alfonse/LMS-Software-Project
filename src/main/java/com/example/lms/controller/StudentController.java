package com.example.lms.controller;

import com.example.lms.model.user_related.Student;
import com.example.lms.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @PostMapping("/add/student")
    public Student addStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
    }

    @PostMapping("/delete/student")
    public void deleteStudent(@RequestParam long id) {
        studentService.deleteStudent(id);
    }

    @PostMapping("/update/student/{id}")
    public void updateStudent(@PathVariable long id, @RequestBody Student student) {
        studentService.updateStudent(id, student);
    }

    @GetMapping("/show/student")
    public List<Student> getAllStudents() {
        return studentService.getAll();
    }
}