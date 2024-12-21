package com.example.lms.repository;

import com.example.lms.model.course_related.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonRepo extends JpaRepository<Lesson, Integer> {
    List<Lesson> findByCourseId(int courseId);

}