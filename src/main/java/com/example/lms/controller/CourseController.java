package com.example.lms.controller;

import com.example.lms.dto.CourseData;
import com.example.lms.model.course_related.Course;
import com.example.lms.model.course_related.FileEntity;
import com.example.lms.model.user_related.User;
import com.example.lms.service.CourseService;
import com.example.lms.service.JwtService;
import com.example.lms.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequestMapping("/course")
@RestController
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;
    private final JwtService jwtService;
    private final UserService userService;

    @PostMapping("/create")
    @PreAuthorize("hasAnyAuthority('INSTRUCTOR')")
    public Course addCourse(@RequestBody CourseData course, HttpServletRequest request){
        String authHeader = request.getHeader("Authorization");
        String token = authHeader.substring(7);
        String email = jwtService.extractUserName(token);
        User user = userService.getUserByEmail(email);
        if (user == null)
            return null;
        course.setInstructorId(user.getId());
        return courseService.addCourse(course);
    }

    @PostMapping("/{id}/upload_file")
    @PreAuthorize("hasAnyAuthority('INSTRUCTOR')")
    public FileEntity uploadFile(@PathVariable int id, @RequestParam("file") MultipartFile file) throws IOException {
        return courseService.uploadFile(id, file);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('INSTRUCTOR', 'ADMIN', 'STUDENT')")
    public Course getCourseById(@PathVariable int id) {
        return courseService.getCourseById(id);
    }

    @GetMapping("/all")
    @PreAuthorize("hasAnyAuthority('INSTRUCTOR', 'ADMIN', 'STUDENT')")
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    @PostMapping("/{courseId}/enroll")
    @PreAuthorize("hasAnyAuthority('STUDENT')")
    public String enrollInCourse(@PathVariable int courseId, HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        String token = authHeader.substring(7);
        String email = jwtService.extractUserName(token);
        User user = userService.getUserByEmail(email);
        if (user == null)
            return null;
        return courseService.enrollInCourse(user.getId(), courseId);
    }

    @DeleteMapping("/{courseId}/delete")
    @PreAuthorize("hasAnyAuthority('INSTRUCTOR', 'ADMIN')")
    public void deleteCourse(@PathVariable("courseId") int courseId){
        courseService.deleteCourse(courseId);
    }

    @PatchMapping("{courseId}/update")
    @PreAuthorize("hasAnyAuthority('INSTRUCTOR', 'ADMIN')")
    public Course updateCourse(@PathVariable int courseId, @RequestBody CourseData courseData){
        return courseService.updateCourse(courseId, courseData);
    }
}
