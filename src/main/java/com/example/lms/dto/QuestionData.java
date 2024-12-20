package com.example.lms.dto;

import com.example.lms.model.course_related.quiz_related.Question;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class QuestionData {
    private int id;
    private String type;
    private String question;
    private String answer;

    public QuestionData(Question question) {
        this.id = question.getId();
        this.type = question.getType();
        this.question = question.getQuestion();
        this.answer = question.getAnswer();
    }
}
