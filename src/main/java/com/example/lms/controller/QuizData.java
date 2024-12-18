package com.example.lms.controller;

import com.example.lms.model.course_related.Question;
import com.example.lms.model.course_related.Quiz;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class QuizData {
    private String title;

    private int score;

    private int numberOfQuestions;

    private int courseId;

    List<QuestionData> questionData = new ArrayList<>();

    public QuizData(Quiz quiz){
        this.title = quiz.getTitle();
        this.score = quiz.getScore();
        this.numberOfQuestions = quiz.getNumberOfQuestions();
        this.courseId = quiz.getCourseId();
        for(Question q : quiz.getQuestions()){
            questionData.add(new QuestionData(q.getType(), q.getQuestion(), q.getAnswer(), q.getQuestionsBank().getId()));
        }
    }
}
