package com.example.lms.repository;

import com.example.lms.model.course_related.quiz_related.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepo extends JpaRepository<Question, Integer> {
}
