package com.example.lms.service;

import com.example.lms.controller.QuestionData;
import com.example.lms.model.course_related.Course;
import com.example.lms.model.course_related.Question;
import com.example.lms.model.course_related.QuestionsBank;
import com.example.lms.repository.CourseRepo;
import com.example.lms.repository.QuestionBankRepo;
import com.example.lms.repository.QuestionRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionBankServiceImpl implements QuestionBankService{

    private final QuestionBankRepo questionBankRepo;
    private final CourseService courseService;

    @Override
    public List<QuestionData> getQuestionBankQuestion(int id){
        Optional<QuestionsBank> questionsBank = questionBankRepo.findById(id);
        List<QuestionData> questionDataReturned = new ArrayList<>();
        if(questionsBank.isPresent()){
            List<Question> questions = questionsBank.get().getQuestions();
            for(Question q : questions){
                QuestionData questionData = new QuestionData(q.getType(), q.getQuestion(), q.getAnswer(), q.getQuestionsBank().getId());
                questionDataReturned.add(questionData);
            }
        }

        return questionDataReturned;
    }

    @Override
    public QuestionsBank getQuestionBank(int id){
        Optional<QuestionsBank> questionsBank = questionBankRepo.findById(id);
        return questionsBank.orElse(null);
    }

    @Override
    public QuestionsBank addQuestionBank(int courseId){
        Course course = courseService.getCourse(courseId);
        List<Question> questions = new ArrayList<>();
        if(course != null){
            QuestionsBank questionsBank = new QuestionsBank(course, questions);
            course.setQuestionsBank(questionsBank);
            courseService.saveCourse(course);
            return questionBankRepo.save(questionsBank);
        }
        return null;
    }

    @Override
    public void saveQuestionBank(QuestionsBank questionsBank){
        questionBankRepo.save(questionsBank);
    }
}

