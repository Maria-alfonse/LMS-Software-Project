package com.example.project.model.Question;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MCQ implements Question{

    private String question;

    private String answer;

    @Override
    public String getType() {
        return "MCQ";
    }

    @Override
    public String getQuestion() {
        return question;
    }

    @Override
    public String getAnswer() {
        return answer;
    }

    @Override
    public Boolean validateAnswer(String answer){
        return this.answer.equals(answer);
    }
}
