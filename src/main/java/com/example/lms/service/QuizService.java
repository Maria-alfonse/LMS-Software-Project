package com.example.lms.service;

import com.example.lms.controller.QuizData;
import com.example.lms.model.course_related.Question;
import com.example.lms.model.course_related.Quiz;

import java.util.ArrayList;
import java.util.List;

public interface QuizService {

    QuizData createQuiz(Quiz quiz);
    int gradeQuiz(int userId, int id, List<String> answers);
}
