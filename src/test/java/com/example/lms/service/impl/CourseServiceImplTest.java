package com.example.lms.service.impl;

import com.example.lms.dto.CourseData;
import com.example.lms.model.course_related.Course;
import com.example.lms.model.user_related.Instructor;
import com.example.lms.repository.CourseRepo;
import com.example.lms.service.InstructorService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import static org.assertj.core.api.Assertions.assertThat;


import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class CourseServiceImplTest {

    @Mock
    private CourseRepo courseRepo;

    @Mock
    private InstructorService instructorService;

    @InjectMocks
    private CourseServiceImpl courseService;

    AutoCloseable autoCloseable;
    private String token;
    private String invalidToken;
    private String SECRET_KEY = "KM1sFsOnlHXvd5pNQvth1fjEMwFQGTCmZabS2OyUKmessiisyouruncle=";

    private CourseData courseData;
    private Instructor instructor;

    private Course course;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        token = Jwts.builder().setSubject("marcelino@mail.com").claim("role", "ADMIN")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24 * 60 * 14))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY.getBytes()).compact();
        invalidToken = "blabla";
        courseData = new CourseData();
        courseData.setInstructorId(1);
        courseData.setDuration(3);
        courseData.setTitle("Software");
        courseData.setDescription("Making software applications");

        instructor = new Instructor();
        instructor.setName("Abanob");
        instructor.setEmail("abanob@mail.com");
        instructor.setId(1);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void addCourse() {
        course = new Course(courseData, instructor);

        Mockito.when(instructorService.getInstructor(1)).thenReturn(instructor);
        Mockito.when(courseService.addCourse(courseData)).thenReturn(course);
        Mockito.when(courseRepo.save(ArgumentMatchers.any())).thenReturn(course);

        CourseData newCourseData = new CourseData();
        newCourseData.setInstructorId(1);
        newCourseData.setDuration(3);
        newCourseData.setTitle("Software");
        newCourseData.setDescription("Making software applications");
        courseService.addCourse(newCourseData);

        assertThat(newCourseData.getDescription()).isEqualTo(courseData.getDescription());
    }
}