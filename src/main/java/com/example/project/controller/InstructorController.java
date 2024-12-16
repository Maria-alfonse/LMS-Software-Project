package com.example.project.controller;

import com.example.project.model.Course.Course;
import com.example.project.service.CourseService;
import com.example.project.service.InstructorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class InstructorController {
    private final InstructorService instructorService;
    private final CourseService courseService;

    @PostMapping("/create/course")
    public void createCourse(@RequestBody Course course) {
        courseService.addCourse(course);
    }
}
