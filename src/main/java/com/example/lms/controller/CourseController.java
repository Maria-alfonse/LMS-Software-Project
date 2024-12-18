package com.example.lms.controller;

import com.example.lms.model.course_related.Course;
import com.example.lms.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/course")
@RestController
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @PostMapping("/create")
    public Course addCourse(@RequestBody CourseData course){
        return courseService.addCourse(course);
    }
}
