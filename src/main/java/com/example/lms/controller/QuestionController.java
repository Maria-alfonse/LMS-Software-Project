package com.example.lms.controller;

import com.example.lms.model.course_related.Question;
import com.example.lms.service.QuestionService;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/question")
public class QuestionController {
    private final QuestionService questionService;

    @PostMapping("/create")
    public QuestionData createQuestion(@RequestBody QuestionData question){
        Question newQuestion = questionService.createQuestion(question);
        return new QuestionData(newQuestion.getType(), newQuestion.getQuestion(), newQuestion.getAnswer(), question.getQuestionBankId());
    }

    @GetMapping("/grade/{id}")
    public Boolean gradeQuestion(@PathVariable("id") int id, @RequestBody String answer){
        return questionService.gradeQuestion(id, answer);
    }

    @GetMapping("/{id}")
    public QuestionData getQuestion(@PathVariable("id") int id){
        Question newQuestion = questionService.getQuestion(id);
        return new QuestionData(newQuestion.getType(), newQuestion.getQuestion(), newQuestion.getAnswer(), newQuestion.getQuestionsBank().getId());
    }
}
