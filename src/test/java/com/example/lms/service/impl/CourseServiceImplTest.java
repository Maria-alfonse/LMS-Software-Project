package com.example.lms.service.impl;

import com.example.lms.dto.CourseData;
import com.example.lms.model.course_related.Course;
import com.example.lms.model.user_related.Instructor;
import com.example.lms.model.user_related.Student;
import com.example.lms.repository.CourseRepo;
import com.example.lms.repository.FileRepo;
import com.example.lms.service.*;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CourseServiceImplTest {

    @Mock
    private CourseRepo courseRepo;

    @Mock
    private InstructorService instructorService;

    @Mock
    private FileService fileService;

    @Mock
    private FileRepo fileRepo;

    @Mock
    private StudentService studentService;

    @Mock
    private NotificationService notificationService;

    @InjectMocks
    private CourseServiceImpl courseService;

    @Captor
    private ArgumentCaptor<Course> courseCaptor;

    AutoCloseable autoCloseable;
    private String token;
    private String invalidToken;
    private final String SECRET_KEY = "KM1sFsOnlHXvd5pNQvth1fjEMwFQGTCmZabS2OyUKmessiisyouruncle=";

    private CourseData courseData;
    private Instructor instructor;
    private Course course;
    private Student student;
    private List<Student> students = new ArrayList<>();

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

        student = new Student();
        student.setId(1);
        student.setName("Mariam");
        student.setEmail("mariam@gmail.com");
        students.add(student);

        course = new Course();
        course.setId(1);
        course.setTitle("Software");
        course.setDescription("Making software applications");
        course.setDuration(3);
        course.setInstructor(instructor);
//        course.setStudents(new ArrayList<>(students));
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void addCourse() {
        Course newCourse = new Course(courseData, instructor);

        when(instructorService.getInstructor(1)).thenReturn(instructor);
        when(courseRepo.save(any(Course.class))).thenReturn(newCourse);

        Course result = courseService.addCourse(courseData);

        assertThat(result).isNotNull();
        assertThat(result.getTitle()).isEqualTo(courseData.getTitle());
        assertThat(result.getDescription()).isEqualTo(courseData.getDescription());
        assertThat(result.getDuration()).isEqualTo(courseData.getDuration());
        assertThat(result.getInstructor()).isEqualTo(instructor);

    }

    @Test
    void getCourseByIdWhenCourseExists() {
        when(courseRepo.findById(1)).thenReturn(Optional.of(course));

        Course foundCourse = courseService.getCourse(1);

        assertThat(foundCourse).isNotNull();
        assertThat(foundCourse.getId()).isEqualTo(1);
        assertThat(foundCourse.getTitle()).isEqualTo("Software");

    }

    @Test
    void getCourseWhenCourseDoesNotExist() {
        when(courseRepo.findById(2)).thenReturn(Optional.empty());

        Course foundCourse = courseService.getCourse(2);

        assertThat(foundCourse).isNull();

    }
    @Test
    void updateCourse() {
        CourseData updateData = new CourseData();
        updateData.setTitle("Advanced Software");
        updateData.setDescription("Advanced topics in software development");
        updateData.setDuration(5);
        updateData.setInstructorId(2);

        Instructor newInstructor = new Instructor();
        newInstructor.setId(2);
        newInstructor.setName("Abanob Essam");
        newInstructor.setEmail("AbanobEssam@gmail.com");

        when(courseRepo.findById(1)).thenReturn(Optional.of(course));
        when(instructorService.getInstructor(2)).thenReturn(newInstructor);
        when(courseRepo.save(any(Course.class))).thenReturn(course);

        Course updatedCourse = courseService.updateCourse(1, updateData);

        assertThat(updatedCourse).isNotNull();
        assertThat(updatedCourse.getTitle()).isEqualTo("Advanced Software");
        assertThat(updatedCourse.getDescription()).isEqualTo("Advanced topics in software development");
        assertThat(updatedCourse.getDuration()).isEqualTo(5);
        assertThat(updatedCourse.getInstructor()).isEqualTo(newInstructor);

        verify(courseRepo, times(1)).findById(1);
        verify(instructorService, times(1)).getInstructor(2);
        verify(courseRepo, times(1)).save(courseCaptor.capture());
        Course capturedCourse = courseCaptor.getValue();
        assertThat(capturedCourse.getTitle()).isEqualTo("Advanced Software");
    }
    @Test
    void updateCourseWhenCourseDoesNotExist() {
        CourseData updateData = new CourseData();
        updateData.setTitle("Not exiest");

        when(courseRepo.findById(3)).thenReturn(Optional.empty());

        Course updatedCourse = courseService.updateCourse(3, updateData);

        assertThat(updatedCourse).isNull();

    }

    @Test
    void getCourseById() {
        when(courseRepo.findById(1)).thenReturn(Optional.of(course));

        Course foundCourse = courseService.getCourseById(1);

        assertThat(foundCourse).isNotNull();
        assertThat(foundCourse.getId()).isEqualTo(1);

    }

    @Test
    void getCourseByIdWhenCourseDoesNotExist() {
        when(courseRepo.findById(2)).thenReturn(Optional.empty());

        Course foundCourse = courseService.getCourseById(2);

        assertThat(foundCourse).isNull();

    }

    @Test
    void getAllCourses() {
        List<Course> courseList = Arrays.asList(course, new Course());
        when(courseRepo.findAll()).thenReturn(courseList);

        List<Course> result = courseService.getAllCourses();

        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(course);

    }

    @Test
    void enrollWhenStudentAndCourseExistandNotEnrolled() {
        int studentId = 1;
        int courseId = 1;

        when(studentService.getStudent(studentId)).thenReturn(student);
        when(courseRepo.findById(courseId)).thenReturn(Optional.of(course));
        when(courseRepo.save(course)).thenReturn(course);

        String response = courseService.enrollInCourse(studentId, courseId);

        assertThat(response).isEqualTo("Student successfully enrolled in the course.");
        assertThat(course.getStudents()).contains(student);

    }

    @Test
    void enrollWhenStudentDoesNotExist() {
        int studentId = 2;
        int courseId = 1;

        when(studentService.getStudent(studentId)).thenReturn(null);

        String response = courseService.enrollInCourse(studentId, courseId);

        assertThat(response).isNull();

    }

    @Test
    void enrollWhenCourseDoesNotExist() {
        int studentId = 1;
        int courseId = 2;

        when(studentService.getStudent(studentId)).thenReturn(student);
        when(courseRepo.findById(courseId)).thenThrow(new IllegalArgumentException("Course not found"));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            courseService.enrollInCourse(studentId, courseId);
        });

        assertThat(exception.getMessage()).isEqualTo("Course not found");

    }

}