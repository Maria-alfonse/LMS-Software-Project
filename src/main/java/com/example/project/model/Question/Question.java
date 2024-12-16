package com.example.project.model.Question;

public interface Question {
    String getType();

    String getQuestion();

    String getAnswer();

    Boolean validateAnswer(String answer);
}
