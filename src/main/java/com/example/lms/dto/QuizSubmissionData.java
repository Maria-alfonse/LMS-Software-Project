package com.example.lms.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class QuizSubmissionData {
    private int id;
    private int grade;
    private int total;
}
