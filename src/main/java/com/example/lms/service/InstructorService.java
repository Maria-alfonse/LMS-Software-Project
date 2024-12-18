package com.example.lms.service;

import com.example.lms.model.user_related.Instructor;

import java.util.List;

public interface InstructorService {
    Instructor addInstructor(Instructor instructor);

    List<Instructor> getAll();

    Instructor getInstructor(int id);
}
