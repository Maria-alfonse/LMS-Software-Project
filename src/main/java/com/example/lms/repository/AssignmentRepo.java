package com.example.lms.repository;

import com.example.lms.model.course_related.assignment_related.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssignmentRepo extends JpaRepository<Assignment, Integer> {
}
