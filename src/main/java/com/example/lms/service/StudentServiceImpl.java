package com.example.lms.service;

import com.example.lms.model.user_related.Student;
import com.example.lms.repository.StudentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepo studentRepo;

    @Override
    public Student addStudent(Student student) {
        Optional<Student> existingStudent = studentRepo.findByEmail(student.getEmail());
        if (existingStudent.isPresent()) {
            throw new IllegalArgumentException("Student with the same email already exists.");
        }
        return studentRepo.save(student);
    }

    @Override
    public void deleteStudent(Integer id) {
        Optional<Student> student = studentRepo.findById(id);
        if (student.isEmpty()) {
            throw new IllegalArgumentException("Student with ID " + id + " does not exist.");
        }
        studentRepo.deleteById(id);
    }

    @Override
    public Student getStudent(int id) {
        Optional<Student> student = studentRepo.findById(id);
        return student.orElse(null);
    }

    @Override
    public void updateStudent(Integer id, Student updatedStudent) {
        Optional<Student> existingStudent = studentRepo.findById(id);
        if (existingStudent.isEmpty()) {
            throw new IllegalArgumentException("Student with ID " + id + " does not exist.");
        }

        if (!Objects.equals(updatedStudent.getEmail(), existingStudent.get().getEmail())) {
            Optional<Student> existingEmail = studentRepo.findByEmail(updatedStudent.getEmail());
            if (existingEmail.isPresent()) {
                throw new IllegalArgumentException("Student with the same email already exists.");
            }
        }

        Student student = existingStudent.get();

        if (updatedStudent.getName() != null)
            student.setName(updatedStudent.getName());

        if (updatedStudent.getEmail() != null)
            student.setEmail(updatedStudent.getEmail());

        if (updatedStudent.getPassword() != null)
            student.setPassword(updatedStudent.getPassword());

        studentRepo.save(student);
    }

    @Override
    public List<Student> getAll() {
        return studentRepo.findAll();
    }
}