package com.example.project.model.Course;

import com.example.project.model.Users.Student;
import jakarta.persistence.*;
import com.example.project.model.Question.Question;
import lombok.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Table (name = "Course")
@Entity
public class Course {




    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private String title;
    private String description;
    private Integer duration;
    private Integer instructorId;

    @OneToMany(mappedBy = "Course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Student> enrolledStudent = new ArrayList<>();

    @OneToMany(mappedBy = "Course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Quiz> quizzes = new ArrayList<>();

    @OneToMany(mappedBy = "Course", cascade = CascadeType.ALL, orphanRemoval = true)
    private Map<Integer, Assignment> assignments = new HashMap<>();

    @OneToMany(mappedBy = "Course", cascade = CascadeType.ALL, orphanRemoval = true)
    private Map<Integer, Lesson> lessons = new HashMap<>();

    @OneToOne(mappedBy = "course", cascade = CascadeType.ALL)
    private QuestionsBank questionsBank;
}

