package com.example.lms.service;

import com.example.lms.dto.QuestionData;
import com.example.lms.dto.QuizData;
import com.example.lms.dto.QuizSubmissionData;
import com.example.lms.model.course_related.Course;
import com.example.lms.model.course_related.quiz_related.Question;
import com.example.lms.model.course_related.quiz_related.Quiz;
import com.example.lms.model.course_related.quiz_related.QuizSubmission;
import com.example.lms.model.user_related.Student;
import com.example.lms.repository.QuizRepo;
import com.example.lms.repository.QuizSubmissionRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService {
    private final QuizRepo quizRepo;

    private final CourseService courseService;

    private final QuizSubmissionRepo quizSubmissionRepo;

    private final StudentService studentService;

    @Override
    public QuizData addQuiz(int courseId, QuizData quizData) {
        Course course = courseService.getCourse(courseId);

        Quiz quiz = new Quiz();
        quiz.setTitle(quizData.getTitle());
        quiz.setGrade(quizData.getGrade());

        List<Question> allQuestions = course.getQuestionBank();

        Collections.shuffle(allQuestions);

        quiz.setCourse(course);

        course.getQuizzes().add(quiz);

        for (int i = 0; i < quizData.getNum(); i++) {
            Question q = allQuestions.get(i);
            quiz.getQuestions().add(q);

            QuestionData questionData = new QuestionData(q);
            quizData.getQuestions().add(questionData);

            q.getQuizzes().add(quiz);
        }

        quizRepo.save(quiz);

        quizData.setId(quiz.getId());

        return quizData;
    }

    @Override
    public QuizSubmissionData submitQuiz(int studentId, int quizId, List<String> answers) {
        Quiz quiz = quizRepo.findById(quizId).orElse(null);

        if (quiz == null)
            return null;

        int score = 0, i = 0;

        for (Question q : quiz.getQuestions()) {
            if (q.getAnswer().equals(answers.get(i++)))
                score++;
        }

        QuizSubmission quizSubmission = new QuizSubmission();

        quizSubmission.setQuiz(quiz);
        quizSubmission.setGrade(score);

        Student student = studentService.getStudent(studentId);

        quizSubmission.setStudent(student);

        student.getQuizSubmissions().add(quizSubmission);

        quiz.getQuizSubmissions().add(quizSubmission);

        quizSubmissionRepo.save(quizSubmission);

        QuizSubmissionData qsd = new QuizSubmissionData();

        qsd.setId(quizSubmission.getId());
        qsd.setGrade(score);
        qsd.setTotal(quiz.getGrade());

        return qsd;
    }

    @Override
    public List<QuizSubmission> getSubmissions(int id) {
        Quiz quiz = quizRepo.findById(id).orElse(null);

        if (quiz == null)
            return null;

        return quiz.getQuizSubmissions();
    }
}
