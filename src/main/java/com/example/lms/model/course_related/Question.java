package com.example.lms.model.course_related;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "Question")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String type;

    private String question;

    private String answer;
}
