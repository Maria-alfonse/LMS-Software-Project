package com.example.lms.service;

import com.example.lms.controller.CourseData;
import com.example.lms.model.course_related.Course;

public interface CourseService {
    Course addCourse(CourseData course);

    Course getCourse(int id);

    void saveCourse(Course course);
    
    void deleteCourse(Integer id);

    void updateCourse(CourseData course);

    //create delete update display

}