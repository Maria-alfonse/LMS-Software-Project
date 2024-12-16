package com.example.project.service.Course;

import com.example.project.model.Course.Course;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ViewCoursesService {
    List<String> getCourses();
}
