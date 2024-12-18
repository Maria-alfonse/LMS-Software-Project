package com.example.lms.service;

import com.example.lms.controller.QuizData;
import com.example.lms.model.QuizSubmission;
import com.example.lms.model.course_related.Course;
import com.example.lms.model.course_related.Question;
import com.example.lms.model.course_related.QuestionsBank;
import com.example.lms.model.course_related.Quiz;
import com.example.lms.model.user_related.Student;
import com.example.lms.repository.QuizRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService{
    private final QuizRepo quizRepo;
    private final CourseService courseService;
    private final StudentService studentService;

    @Override
    public QuizData createQuiz(Quiz quiz){
        Course course = courseService.getCourse(quiz.getCourseId());
        QuestionsBank questionsBank = course.getQuestionsBank();
        List<Question> allQuestions = questionsBank.getQuestions();

        if (allQuestions.size() < quiz.getNumberOfQuestions()) {
            return null;
        }

        Collections.shuffle(allQuestions);
        List<Question> selectedQuestions = allQuestions.stream()
                .limit(quiz.getNumberOfQuestions())
                .toList();

        selectedQuestions.forEach(question -> question.setQuiz(quiz));
        quiz.setQuestions(selectedQuestions);
        quizRepo.save(quiz);
        return new QuizData(quiz);
    }

    @Override
    public int gradeQuiz(int userId, int id, List<String> answers){
        Optional<Quiz> gradedQuiz = quizRepo.findById(id);
        if(gradedQuiz.isPresent()){
            List<Question> quizQuestion = gradedQuiz.get().getQuestions();
            int sz = gradedQuiz.get().getNumberOfQuestions();
            int score = 0;
            int i = 0;
            for(Question q : gradedQuiz.get().getQuestions()){
                if(q.getAnswer().equals(answers.get(i)))
                    score++;
                i++;
            }
            QuizSubmission quizSubmission = new QuizSubmission();
            Student student = studentService.getStudent(userId);
            quizSubmission.setGrade(score);
            quizSubmission.setStudent(student);
            quizSubmission.setQuiz(gradedQuiz.get());
            return score;
        }
        return 0;
    }
}
