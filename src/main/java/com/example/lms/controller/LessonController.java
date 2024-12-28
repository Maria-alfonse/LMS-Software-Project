package com.example.lms.controller;

import com.example.lms.dto.AttendanceData;
import com.example.lms.model.course_related.Lesson;
import com.example.lms.model.user_related.User;
import com.example.lms.service.JwtService;
import com.example.lms.service.LessonService;
import com.example.lms.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/lesson")
@RestController
@RequiredArgsConstructor
public class LessonController {
    private final LessonService lessonService;
    private final JwtService jwtService;
    private final UserService userService;

    @PostMapping("/{courseId}/add")
    @PreAuthorize("hasAnyAuthority('INSTRUCTOR')")
    public Lesson addLessonToCourse(@RequestBody Lesson lesson, @PathVariable int courseId) {
        return lessonService.addLesson(lesson, courseId);
    }

    @GetMapping("/{courseId}/show_lessons")
    @PreAuthorize("hasAnyAuthority('INSTRUCTOR', 'ADMIN', 'STUDENT')")
    public List<Lesson> getLessonsForCourse(@PathVariable int courseId) {
        return lessonService.getLessonsForCourse(courseId);
    }

    @PostMapping("/{lessonId}/attend")
    @PreAuthorize("hasAnyAuthority('STUDENT')")
    public String attendLesson(
            @PathVariable int lessonId,
            @RequestBody AttendanceData attendanceData, HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        String token = authHeader.substring(7);
        String email = jwtService.extractUserName(token);
        User user = userService.getUserByEmail(email);
        return lessonService.attendLesson(
                lessonId,
                user.getId(),
                attendanceData.getOtp()
        );
    }

}
