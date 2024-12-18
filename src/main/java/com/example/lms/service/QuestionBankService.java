package com.example.lms.service;

import com.example.lms.controller.QuestionData;
import com.example.lms.model.course_related.QuestionsBank;

import java.util.List;

public interface QuestionBankService {

    QuestionsBank addQuestionBank(int id);
    List<QuestionData> getQuestionBankQuestion(int id);
    QuestionsBank getQuestionBank(int id);

    void saveQuestionBank(QuestionsBank questionsBank);

}
