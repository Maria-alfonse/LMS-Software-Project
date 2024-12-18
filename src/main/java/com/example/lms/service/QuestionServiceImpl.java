package com.example.lms.service;

import com.example.lms.controller.QuestionData;
import com.example.lms.model.course_related.Question;
import com.example.lms.model.course_related.QuestionsBank;
import com.example.lms.repository.QuestionBankRepo;
import com.example.lms.repository.QuestionRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService{

    private final QuestionRepo questionRepo;
    private final QuestionBankService questionBankService;
    @Override
    public Question createQuestion(QuestionData question){
        QuestionsBank questionsBank = questionBankService.getQuestionBank(question.getQuestionBankId());
        Question q = new Question(question, questionBankService.getQuestionBank(questionsBank.getId()));
        questionsBank.getQuestions().add(q);
        questionBankService.saveQuestionBank(questionsBank);
        return questionRepo.save(q);
    }

    @Override
    public Boolean gradeQuestion(int id, String answer){
        Optional<Question> question = questionRepo.findById(id);
        return question.map(value -> value.getAnswer().equals(answer)).orElse(false);
    }

    @Override
    public Question getQuestion(int id){
        Optional<Question> q = questionRepo.findById(id);
        return q.orElse(null);
    }
}
