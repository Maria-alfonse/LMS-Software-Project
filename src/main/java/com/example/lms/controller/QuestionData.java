package com.example.lms.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
public class QuestionData {
    private String type;

    private String question;

    private String answer;

    private Integer questionBankId;

    public QuestionData(String type, String question, String answer, Integer questionBankId){
        this.type = type;
        this.question = question;
        this.answer = answer;
        this.questionBankId = questionBankId;
    }
}
