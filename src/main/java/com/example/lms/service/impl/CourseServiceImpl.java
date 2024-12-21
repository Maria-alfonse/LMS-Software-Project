package com.example.lms.service.impl;

import com.example.lms.dto.CourseData;
import com.example.lms.model.course_related.Course;
import com.example.lms.model.user_related.Instructor;
import com.example.lms.repository.CourseRepo;
import com.example.lms.service.CourseService;
import com.example.lms.service.InstructorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepo courseRepo;

    private final InstructorService instructorService;
    @Override
    public Course addCourse(CourseData course) {
        Instructor instructor = instructorService.getInstructor(course.getInstructorId());
        Course newCourse = new Course(course, instructor);

        return courseRepo.save(newCourse);
    }

    @Override
    public Course getCourse(int id) {
        return courseRepo.findById(id).orElse(null);
    }

    @Override
    public void saveCourse(Course course) {
        courseRepo.save(course);
    }
}