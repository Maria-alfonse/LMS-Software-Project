package com.example.lms.repository;

import com.example.lms.model.course_related.Course;
import com.example.lms.model.user_related.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepo extends JpaRepository<Course, Integer> {
    @Query("SELECT c.instructor FROM Course c WHERE c.id = :courseId")
    Instructor getInstructor(@Param("courseId") int courseId);
}
