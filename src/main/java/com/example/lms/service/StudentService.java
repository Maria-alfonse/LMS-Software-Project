package com.example.lms.service;

import com.example.lms.model.user_related.Student;
import com.example.lms.dto.UserData;

public interface StudentService {
    Student getStudent(int id);

    UserData addStudent(Student student);
}
