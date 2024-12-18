package com.example.lms.controller;

import com.example.lms.model.course_related.QuestionsBank;
import com.example.lms.service.QuestionBankService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/questionbank")
@RestController
@RequiredArgsConstructor
public class QuestionBankController {
    private final QuestionBankService questionBankService;

    @PostMapping("/create/{id}")
    public void addQuestionBank(@PathVariable("id") int courseId){
        questionBankService.addQuestionBank(courseId);
    }

    @GetMapping("/get/{id}")
    public List<QuestionData> getQuestionBank(@PathVariable("id") int id){
        return questionBankService.getQuestionBankQuestion(id);
    }
}
