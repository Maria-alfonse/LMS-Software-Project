package com.example.project.model.Question;

public class TrueFalse implements Question{

    private String question;

    private String answer;

    @Override
    public String getType() {
        return "True/False";
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
    public Boolean validateAnswer(String answer) {
        return this.answer.equals(answer);
    }
}
