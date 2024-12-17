package com.example.project.model.Course;

import com.example.project.model.Question.Question;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@Table(name = "Quiz")
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;


    private final List<Question> questions = new ArrayList<>();
}