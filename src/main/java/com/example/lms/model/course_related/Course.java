package com.example.lms.model.course_related;

import com.example.lms.controller.CourseData;
import com.example.lms.model.course_related.assignment_related.Assignment;
import com.example.lms.model.course_related.quiz_related.Question;
import com.example.lms.model.course_related.quiz_related.Quiz;
import com.example.lms.model.user_related.Instructor;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Table (name = "Course")
@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "duration")
    private Integer duration;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

    public Course(CourseData courseData, Instructor instructor) {
        this.title = courseData.getTitle();
        this.description = courseData.getDescription();
        this.duration = courseData.getDuration();
        this.instructor = instructor;
    }

//    @OneToMany(mappedBy = "Course", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Student> enrolledStudent = new ArrayList<>();
//
    @OneToMany(mappedBy = "course")
    @JsonManagedReference
    private List<Assignment> assignments = new ArrayList<>();

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Question> questionBank = new ArrayList<>();

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Quiz> quizzes = new ArrayList<>();

//
//    @OneToMany(mappedBy = "Course", cascade = CascadeType.ALL, orphanRemoval = true)
//    private Map<Integer, Lesson> lessons = new HashMap<>();
//
}

