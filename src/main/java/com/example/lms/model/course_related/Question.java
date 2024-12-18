package com.example.lms.model.course_related;

import com.example.lms.controller.QuestionData;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "Question")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String type;

    private String question;

    private String answer;

    @ManyToOne
    @JoinColumn(name = "questionsBank_id")
    private QuestionsBank questionsBank;

    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

    public Question(QuestionData questionData, QuestionsBank questionsBank){
        this.type = questionData.getType();
        this.question = questionData.getQuestion();
        this.answer = questionData.getAnswer();
        this.questionsBank = questionsBank;
    }
}
