package com.example.lms.controller;

import com.example.lms.model.course_related.Course;
import com.example.lms.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.lms.model.course_related.FileEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequestMapping("/course")
@RestController
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @PostMapping("/create")
    public Course addCourse(@RequestBody CourseData course){
        return courseService.addCourse(course);
    }
    @PostMapping("/{id}/uploadFile")
    public FileEntity uploadFile(@PathVariable int id, @RequestParam("file") MultipartFile file) throws IOException {
        return courseService.uploadFile(id, file);
    }
    @GetMapping("/all")
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();}
    @GetMapping("/{id}")
    public Course getCourseById(@PathVariable int id) {
        return courseService.getCourseById(id);
    }
    @PostMapping("/{courseId}/enroll")
    public String enrollInCourse(@RequestBody int studentId, @PathVariable int courseId) {
        return courseService.enrollInCourse(studentId, courseId);
    }
}
