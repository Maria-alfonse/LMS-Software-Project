package com.example.project.controller;

import com.example.project.model.Course.Course;
import com.example.project.model.Course.Lesson;
import com.example.project.service.Course.CourseService;
import com.example.project.service.InstructorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class InstructorController {
    private final InstructorService instructorService;
    private final CourseService courseService;

    @PostMapping("/create/course")
    public void createCourse(@RequestBody Course course) {
        courseService.addCourse(course);
    }

    @GetMapping("course/{id}/get/students")
    public List<Integer> getEnrolledStudents(@PathVariable("id") int id){
        return courseService.viewEnrolled(id);
    }

    @PostMapping("course/{id}/lesson/create")
    public int createLesson(@PathVariable("id") int id, @RequestBody Lesson lesson) {
        return courseService.createLesson(id, lesson);
    }
}
