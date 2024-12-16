package com.example.project.model.Course;

import com.example.project.model.Question.Question;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@RequiredArgsConstructor
public class Course {

    public class Assignment {

    }

    public class Quiz {
        private List<Question> questions;
    }

    @RequiredArgsConstructor
    public class QuestionsBank {
        private final List<Question> questions;
    }

    private final int id;
    private final String name;
    private final List<Integer> enrolledStudent;
    private final Integer instructorId;
    private final List<Quiz> quizzes;
    private final List<Assignment> assignments;
}

