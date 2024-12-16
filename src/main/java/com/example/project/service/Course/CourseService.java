package com.example.project.service.Course;

import com.example.project.model.Course.Course;
import com.example.project.model.Course.Lesson;
import com.example.project.model.Users.Student;

import java.util.List;

public interface CourseService {
    void addCourse(Course course);

    List<Integer> viewEnrolled(int courseId);

    int createLesson(int courseId, Lesson lesson);
}
