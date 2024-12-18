package com.example.lms.model.course_related;

import com.example.lms.model.QuizSubmission;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "Quiz")
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    private int score;

    private int numberOfQuestions;

    private int courseId;

    @OneToMany(mappedBy = "question")
    private List<Question> questions = new ArrayList<>();

    @OneToMany(mappedBy = "quiz")
    private List<QuizSubmission> submissions = new ArrayList<>();

}
