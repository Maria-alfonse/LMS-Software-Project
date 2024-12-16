package com.example.project.service;

import com.example.project.model.Course.Course;

import java.util.HashMap;
import java.util.Map;

public class CourseServiceImpl {

    public final Map<Integer, Course> courses = new HashMap<>();
    public void addCourse(Course course){
        courses.put(course.getId(), course);
    }
}
