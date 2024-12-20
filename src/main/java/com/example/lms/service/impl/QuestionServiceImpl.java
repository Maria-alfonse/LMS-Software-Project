package com.example.lms.service.impl;

import com.example.lms.dto.QuestionData;
import com.example.lms.model.course_related.Course;
import com.example.lms.model.course_related.quiz_related.Question;
import com.example.lms.repository.QuestionRepo;
import com.example.lms.service.CourseService;
import com.example.lms.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepo questionRepo;

    private final CourseService courseService;

    @Override
    public QuestionData addQuestion(int courseId, QuestionData questionData) {
        Question question = new Question();
        question.setType(questionData.getType());
        question.setQuestion(questionData.getQuestion());
        question.setAnswer(questionData.getAnswer());

        Course course = courseService.getCourse(courseId);

        question.setCourse(course);
        questionRepo.save(question);

        course.getQuestionBank().add(question);
        courseService.saveCourse(course);

        questionData.setId(question.getId());
        return questionData;
    }
}