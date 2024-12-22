package com.example.lms.repository;

import com.example.lms.model.user_related.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepo extends JpaRepository<Admin, Integer> {
    Optional<Admin> findByEmail(String email);
}
