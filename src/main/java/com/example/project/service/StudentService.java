package com.example.project.service;

import com.example.project.model.Student;

public interface StudentService {
    Boolean addStudent(Student student);

    Boolean deleteStudent(int id);

    Student getStudent(int id);
}