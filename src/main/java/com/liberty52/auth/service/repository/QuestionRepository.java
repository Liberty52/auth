package com.liberty52.auth.service.repository;

import com.liberty52.auth.service.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, String> {
    List<Question> findByWriterId(String writerId);
}