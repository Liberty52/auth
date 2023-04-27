package com.liberty52.auth.service.applicationservice;

import com.liberty52.auth.global.exception.external.NotYourQuestionException;
import com.liberty52.auth.global.exception.external.QuestionNotFoundById;
import com.liberty52.auth.service.controller.dto.QuestionCreateRequestDto;
import com.liberty52.auth.service.entity.Question;
import com.liberty52.auth.service.entity.QuestionStatus;
import com.liberty52.auth.service.repository.QuestionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
public class QuestionDeleteServiceTest {

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    QuestionDeleteService questionDeleteService;

    String writerId = "testId";
    String questionId;

    @BeforeEach
    void init() {
        String title = "제목";
        String content = "내용";
        Question question = Question.create(title, content, writerId);
        questionId = question.getId();
        questionRepository.save(question);
    }

    @Test
    void 문의추가() {

        Question beforeQuestion = questionRepository.findById(questionId).orElseGet(null);
        assertThat(beforeQuestion.getWriterId().equals(writerId));
        Assertions.assertThrows(QuestionNotFoundById.class, () -> questionDeleteService.deleteQuestion(writerId, "err"));
        Assertions.assertThrows(NotYourQuestionException.class, () -> questionDeleteService.deleteQuestion("err", questionId));

        questionDeleteService.deleteQuestion(writerId, questionId);
        Question afterQuestion = questionRepository.findById(questionId).orElse(null);
        Assertions.assertNull(afterQuestion);
    }
}
