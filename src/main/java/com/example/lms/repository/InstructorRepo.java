package com.example.lms.repository;

import com.example.lms.model.user_related.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InstructorRepo extends JpaRepository<Instructor, Integer> {
    Optional<Instructor> findByEmail(String email);

}
