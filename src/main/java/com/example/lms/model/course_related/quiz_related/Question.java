package com.example.lms.model.course_related.quiz_related;

import com.example.lms.model.course_related.Course;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "Question")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @ManyToMany(mappedBy = "questions")
    private List<Quiz> quizzes = new ArrayList<>();

    private String type;
    private String question;
    private String answer;
}
