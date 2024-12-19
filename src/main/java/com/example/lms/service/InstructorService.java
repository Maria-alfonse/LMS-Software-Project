package com.example.lms.service;

import com.example.lms.model.user_related.Instructor;

import java.util.List;

public interface InstructorService {
    Instructor addInstructor(Instructor instructor);

    List<Instructor> getAll();

    Instructor getInstructor(long id);

    void updateInstructor(long id, Instructor instructor);
    void deleteInstructor(long id);

}
