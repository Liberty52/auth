package com.liberty52.auth.service.repository;

import com.liberty52.auth.service.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, String> {
}