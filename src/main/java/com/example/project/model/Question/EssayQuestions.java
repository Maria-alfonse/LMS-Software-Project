package com.example.project.model.Question;

public class EssayQuestions implements Question{

    private String question;
    private String answer;

    @Override
    public String getType() {
        return "Essay";
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
