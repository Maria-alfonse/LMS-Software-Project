package com.example.lms.service;

import com.example.lms.model.user_related.Student;
import com.example.lms.dto.UserData;
import java.util.List;


public interface StudentService {
    Student getStudent(int id);

    UserData addStudent(Student student);

    List<Student> getAll();

    void updateStudent(Integer id, Student student);

    void deleteStudent(Integer id);
}