package com.example.project.service;

import com.example.project.model.Instructor;

public interface InstructorService {
    Boolean addInstructor(Instructor instructor);

    Boolean deleteInstructor(int id);

    Instructor getInstructor(int id);
}
