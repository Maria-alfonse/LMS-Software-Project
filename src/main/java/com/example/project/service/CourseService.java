package com.example.project.service;

import com.example.project.model.Course.Course;
import org.springframework.stereotype.Service;

@Service
public interface CourseService {
    void addCourse(Course course);


}
