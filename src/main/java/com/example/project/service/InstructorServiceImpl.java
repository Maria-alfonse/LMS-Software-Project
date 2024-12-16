package com.example.project.service;

import com.example.project.model.Instructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class InstructorServiceImpl implements InstructorService {
    private final Map<Integer, Instructor> instructors = new HashMap<>();

    @Override
    public Boolean addInstructor(Instructor instructor) {
        if (instructors.containsKey(instructor.getId()))
            return false;
        instructors.put(instructor.getId(), instructor);
        return true;
    }

    @Override
    public Boolean deleteInstructor(int id) {
        if (instructors.containsKey(id)) {
            instructors.remove(id);
            return true;
        }
        return false;
    }

    @Override
    public Instructor getInstructor(int id) {
        return instructors.get(id);
    }
}
