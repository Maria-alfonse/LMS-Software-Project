package com.example.lms.service;

import com.example.lms.controller.CourseData;
import com.example.lms.model.course_related.Course;
import com.example.lms.model.user_related.Instructor;
import com.example.lms.repository.CourseRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService{

    private final CourseRepo courseRepo;

    private final InstructorService instructorService;
    @Override
    public Course addCourse(CourseData course) {
        Instructor instructor = instructorService.getInstructor(course.getInstructorId());

        Course newCourse = new Course(course, instructor);
        return courseRepo.save(newCourse);
    }

    @Override
    public void deleteCourse(Integer id) {

    }

    @Override
    public void updateCourse(CourseData course) {

    }


}