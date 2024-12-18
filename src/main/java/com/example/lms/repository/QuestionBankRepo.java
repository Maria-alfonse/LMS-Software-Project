package com.example.lms.repository;

import com.example.lms.model.course_related.QuestionsBank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionBankRepo extends JpaRepository<QuestionsBank, Integer> {
}
