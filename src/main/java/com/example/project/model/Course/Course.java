package com.example.project.model.Course;

import com.example.project.model.Question.Question;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Setter
@Getter
@RequiredArgsConstructor
public class Course {

    @Getter
    @Setter
    public class Assignment {
        private int id;
    }

    @Setter
    @Getter
    public class Quiz {
        private int id;
        private final List<Question> questions = new ArrayList<>();
    }




    public class QuestionsBank {
        private final List<Question> questions = new ArrayList<>();
    }

    private final int id;
    private final String title;
    private final String description;
    private final Integer duration;
    private final Integer instructorId;

    private final List<Integer> enrolledStudent = new ArrayList<>();
    private final Map<Integer, Quiz> quizzes = new HashMap<>();
    private final Map<Integer, Assignment> assignments = new HashMap<>();
    private final Map<Integer, Lesson> lessons = new HashMap<>();
    private final QuestionsBank questionsBank = new QuestionsBank();
}

