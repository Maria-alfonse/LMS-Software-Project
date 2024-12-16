package com.example.project.controller;

import com.example.project.model.Course.Course;
import com.example.project.service.Course.CourseService;
import com.example.project.service.Course.EnrollCourseService;
import com.example.project.service.Course.ViewCoursesService;
import com.example.project.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/student")
@RestController
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;
    private final ViewCoursesService viewCoursesService;
    private final EnrollCourseService enrollCourseService;

    @GetMapping("/course/get")
    public List<String> getCourses(){
        return viewCoursesService.getCourses();
    }

//    @PostMapping("/course/enroll/{id}")
//    public void enrollCourse(@PathVariable int id){
//        enrollCourseService.enrollCourse(, id);
//    }
}
