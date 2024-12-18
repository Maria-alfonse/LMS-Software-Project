package com.example.lms.service;

import com.example.lms.model.user_related.Instructor;
import com.example.lms.repository.InstructorRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class InstructorServiceImpl implements InstructorService{

    private final InstructorRepo instructorRepo;

    @Override
    public Instructor addInstructor(Instructor instructor) {
        Optional<Instructor> existingInstructor = instructorRepo.findByEmail(instructor.getEmail());
        if (existingInstructor.isPresent()) {
            throw new IllegalArgumentException("Instructor with the same email already exists.");
        }
        return instructorRepo.save(instructor);
    }

    @Override
    public Instructor getInstructor(int id) {
        Optional<Instructor> instructor = instructorRepo.findById(id);
        return instructor.orElse(null);
    }

    @Override
    public List<Instructor> getAll() {
        return instructorRepo.findAll();
    }
}
