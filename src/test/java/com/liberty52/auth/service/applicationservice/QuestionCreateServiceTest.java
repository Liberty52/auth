package com.liberty52.auth.service.applicationservice;

import com.liberty52.auth.service.controller.dto.QuestionCreateRequest;
import com.liberty52.auth.service.entity.Question;
import com.liberty52.auth.service.entity.QuestionStatus;
import com.liberty52.auth.service.repository.QuestionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
public class QuestionCreateServiceTest {

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    QuestionCreateService questionCreateService;

    @Test
    void 문의추가() {
        String writerId = "testId";
        QuestionCreateRequest dto = QuestionCreateRequest.create("제목", "내용");
        questionCreateService.createQuestion(writerId, dto);

        Question question = questionRepository.findByWriterId(writerId).orElseThrow();
        assertThat(question.getTitle()).isEqualTo("제목");
        assertThat(question.getContent()).isEqualTo("내용");
        assertThat(question.getWriterId()).isEqualTo(writerId);
        assertThat(question.getStatus()).isEqualTo(QuestionStatus.WAITING);
        assertThat(question.getCreatedAt().toLocalDate()).isEqualTo(LocalDate.now());
    }
}
