package com.example.lms.service;

import com.example.lms.controller.CourseData;
import com.example.lms.model.course_related.Course;
import com.example.lms.model.course_related.FileEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

public interface CourseService {
    Course addCourse(CourseData course);

    Course getCourse(int id);

    void saveCourse(Course course);
  
    FileEntity uploadFile(int courseId, MultipartFile file) throws IOException;
  
    List<Course> getAllCourses();
  
    Course getCourseById( int id);
  
    String enrollInCourse(int studentId, int courseId);
    
    void deleteCourse(Integer id);

    void updateCourse(CourseData course);

}