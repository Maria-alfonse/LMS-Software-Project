package com.example.lms.service;

import com.example.lms.controller.QuestionData;
import com.example.lms.model.course_related.Question;
import com.example.lms.model.course_related.QuestionsBank;

import java.util.Optional;

public interface QuestionService {

    Question createQuestion(QuestionData questionData);

    Boolean gradeQuestion(int question, String answer);

    Question getQuestion(int id);
}
