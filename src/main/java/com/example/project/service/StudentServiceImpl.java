package com.example.project.service;

import com.example.project.model.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final Map<Integer, Student> students = new HashMap<>();

    private final UserService userService;

    @Override
    public Boolean addStudent(Student student) {
        if (students.containsKey(student.getId()))
            return false;
        students.put(student.getId(), student);
        userService.addUser(student);
        return true;
    }

    @Override
    public Boolean deleteStudent(int id) {
        if (students.containsKey(id)) {
            students.remove(id);
            return true;
        }
        return false;
    }

    @Override
    public Student getStudent(int id) {
        return students.get(id);
    }
}
