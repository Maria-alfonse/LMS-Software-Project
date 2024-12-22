package com.example.lms.service;

import com.example.lms.model.course_related.Lesson;

import java.util.List;

public interface LessonService {
    Lesson addLesson(Lesson lesson, int courseId);
    List<Lesson> getLessonsForCourse(int courseId);;
    String attendLesson(int lessonId, int studentId, int otp);
}
