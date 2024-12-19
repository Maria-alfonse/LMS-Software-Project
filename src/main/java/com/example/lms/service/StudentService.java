package com.example.lms.service;

import com.example.lms.model.user_related.Student;
import java.util.List;

public interface StudentService {
    Student addStudent(Student student);

    List<Student> getAll();

    Student getStudent(Integer id);

    void updateStudent(Integer id, Student student);

    void deleteStudent(Integer id);
}