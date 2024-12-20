package com.example.lms.service;

import com.example.lms.model.user_related.Instructor;
import com.example.lms.repository.InstructorRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class InstructorServiceImpl implements InstructorService{

    private final InstructorRepo instructorRepo;

    @Override
    public void deleteInstructor(Integer id) {
        Optional<Instructor> instructor = instructorRepo.findById(id);
        if (instructor.isEmpty()) {
            throw new IllegalArgumentException("Instructor with ID " + id + " does not exist.");
        }
        instructorRepo.deleteById(id);
    }

    @Override
    public Instructor addInstructor(Instructor instructor) {
        Optional<Instructor> existingInstructor = instructorRepo.findByEmail(instructor.getEmail());
        if (existingInstructor.isPresent()) {
            throw new IllegalArgumentException("Instructor with the same email already exists.");
        }
        return instructorRepo.save(instructor);
    }

    @Override
    public Instructor getInstructor(Integer id) {
        Optional<Instructor> instructor = instructorRepo.findById(id);
        return instructor.orElse(null);
    }

    @Override
    public void updateInstructor(Integer id, Instructor updatedInstructor) {
        Optional<Instructor> existingInstructor = instructorRepo.findById(id);
        if (existingInstructor.isEmpty()) {
            throw new IllegalArgumentException("Instructor with ID " + id + " does not exist.");
        }

        if(!Objects.equals(updatedInstructor.getEmail(), existingInstructor.get().getEmail())){

            Optional<Instructor> existingemail = instructorRepo.findByEmail(updatedInstructor.getEmail());
            if (existingemail.isPresent()) {
                throw new IllegalArgumentException("Instructor with the same email already exists.");
            }
        }
        Instructor instructor = existingInstructor.get();
        if(updatedInstructor.getName() != null)
            instructor.setName(updatedInstructor.getName());
        if(updatedInstructor.getEmail() != null)
            instructor.setEmail(updatedInstructor.getEmail());
        if(updatedInstructor.getPassword() != null)
            instructor.setPassword(updatedInstructor.getPassword());

        instructorRepo.save(instructor);
    }

    @Override
    public List<Instructor> getAll() {
        return instructorRepo.findAll();
    }
}
