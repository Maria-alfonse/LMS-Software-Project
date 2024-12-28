package com.example.lms.service.impl;

import com.example.lms.dto.CourseData;
import com.example.lms.model.course_related.Course;
import com.example.lms.model.course_related.FileEntity;
import com.example.lms.model.user_related.Instructor;
import com.example.lms.model.user_related.Student;
import com.example.lms.repository.CourseRepo;
import com.example.lms.repository.FileRepo;
import com.example.lms.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepo courseRepo;

    private final InstructorService instructorService;

    private final FileService fileService;

    private final FileRepo fileRepo;

    private final StudentService studentService;

    private final NotificationService notificationService;

    private final EmailService emailService;

    @Override
    public Course addCourse(CourseData course) {
        Instructor instructor = instructorService.getInstructor(course.getInstructorId());
        Course newCourse = new Course(course, instructor);
        instructor.getCourse().add(newCourse);
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

    @Override
    public void deleteCourse(Integer id) {
        courseRepo.findById(id).ifPresent(courseRepo::delete);
    }

    @Override
    public Course updateCourse(int courseId, CourseData course) {
        Course newCourse = courseRepo.findById(courseId).orElse(null);
        if(newCourse != null){
            if(course.getTitle() != null)
                newCourse.setTitle(course.getTitle());
            if(course.getDescription() != null)
                newCourse.setDescription(course.getDescription());
            if(course.getDuration() != null)
                newCourse.setDuration(course.getDuration());
            if(course.getInstructorId() != null)
                newCourse.setInstructor(instructorService.getInstructor(course.getInstructorId()));

            return courseRepo.save(newCourse);
        }
        return null;
    }

    @Override
    public FileEntity uploadFile(int courseId, MultipartFile file) throws IOException {
        Course course = courseRepo.findById(courseId)
                .orElseThrow(() -> new NoSuchElementException("Course not found with id " + courseId));

        FileEntity fileEntity = fileService.saveFile(file);

        course.getFiles().add(fileEntity);
        fileEntity.setCourse(course);
        fileRepo.save(fileEntity);
        courseRepo.save(course);

        return fileEntity;
    }

    public Course getCourseById(int id) {
        return courseRepo.findById(id).orElse(null);
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepo.findAll();
    }

    @Override
    public String enrollInCourse(int studentId, int courseId) {
        Student student = studentService.getStudent(studentId);
        if (student == null)
            return null;
        Course course = courseRepo.findById(courseId).orElseThrow(() -> new IllegalArgumentException("Course not found"));

        if (course.getStudents().contains(student)) {
            return "Student is already enrolled in this course.";
        }
        course.getStudents().add(student);
        courseRepo.save(course);

        String text = "You have successfully enrolled in the course: " + course.getTitle();

        notificationService.sendNotification(student, text);
        emailService.sendEmail(student.getEmail(), "Successful Enrollment", text);

        Instructor instructor = course.getInstructor();
        text = "Student " + student.getName() + " has enrolled in your course: " + course.getTitle();
        notificationService.sendNotification(instructor, text);

        emailService.sendEmail(instructor.getEmail(), "New Student Enrollment", text);

        return "Student successfully enrolled in the course.";
    }

}