package com.example.project.service.Course;

import com.example.project.model.Course.Course;
import com.example.project.model.Course.Lesson;
import com.example.project.model.Users.Student;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.*;

@Service
public class CourseServiceImpl implements CourseService, ViewCoursesService, EnrollCourseService {

    private final Map<Integer, Course> courses = new HashMap<>();

    private final SecureRandom secureRandom = new SecureRandom();
    @Override
    public void addCourse(Course course){
        courses.put(course.getId(), course);
    }


    public String toString(Course course) {
        return "ID: " + course.getId() + "   " +
                "Title: " + course.getTitle() + "   " +
                "Description: " + course.getDescription() + "   " +
                "Duration: " + course.getDuration();
    }

    @Override
    public List<String> getCourses() {
        List<String> list = new ArrayList<>();
        for (Course course : courses.values()) {
            list.add(toString(course));
        }
        return list;
    }

    @Override
    public void enrollCourse(int studentId, int courseId) {
        Course course = courses.get(courseId);
        course.getEnrolledStudent().add(studentId);
    }

    @Override
    public List<Integer> viewEnrolled(int courseId) {
        Course course = courses.get(courseId);
        return course.getEnrolledStudent();
    }

    @Override
    public int createLesson(int courseId, Lesson lesson) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            stringBuilder.append(secureRandom.nextInt(10));
        }
        lesson.setOTP(Integer.parseInt(stringBuilder.toString()));
        Course course = courses.get(courseId);
        course.getLessons().put(lesson.getId(), lesson);
        return lesson.getOTP();
    }
}
