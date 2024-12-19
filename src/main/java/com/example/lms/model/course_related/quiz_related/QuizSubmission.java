package com.example.lms.model.course_related.quiz_related;

import com.example.lms.model.user_related.Student;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "QuizSubmission")
public class QuizSubmission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "quiz_id", nullable = false)
    private Quiz quiz;

    private int grade;
}
