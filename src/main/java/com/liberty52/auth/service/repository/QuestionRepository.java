package com.liberty52.auth.service.repository;

import com.liberty52.auth.service.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, String> {
    Optional<Question> findByWriterId(String writerId);
}