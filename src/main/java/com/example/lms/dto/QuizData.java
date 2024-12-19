package com.example.lms.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class QuizData {
    private int id;
    private String title;
    private int grade;
    private int num;
    private List<QuestionData> questions = new ArrayList<>();
}
