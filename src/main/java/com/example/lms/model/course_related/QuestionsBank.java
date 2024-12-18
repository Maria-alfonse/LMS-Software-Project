package com.example.lms.model.course_related;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "QuestionsBank")
public class QuestionsBank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @OneToMany(mappedBy = "questionsBank")
    private List<Question> questions = new ArrayList<>();

    public QuestionsBank(Course course, List<Question> questions){
        this.course = course;
        this.questions = questions;
    }
}
