package com.example.lms.service;

import com.example.lms.controller.CourseData;
import com.example.lms.model.course_related.Course;

public interface CourseService {
    Course addCourse(CourseData course);
}