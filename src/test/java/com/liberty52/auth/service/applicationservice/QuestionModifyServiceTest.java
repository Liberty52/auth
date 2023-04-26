package com.liberty52.auth.service.applicationservice;

import com.liberty52.auth.global.exception.external.InvalidResourceConstraintException;
import com.liberty52.auth.global.exception.external.NotYourQuestionException;
import com.liberty52.auth.global.exception.external.QuestionNotFoundById;
import com.liberty52.auth.global.exception.internal.InvalidQuestionContentException;
import com.liberty52.auth.global.exception.internal.InvalidQuestionTitleException;
import com.liberty52.auth.service.controller.dto.QuestionCreateRequestDto;
import com.liberty52.auth.service.controller.dto.QuestionModifyRequestDto;
import com.liberty52.auth.service.entity.Question;
import com.liberty52.auth.service.repository.QuestionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
public class QuestionModifyServiceTest {

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    QuestionModifyService questionModifyService;

    @Autowired
    QuestionCreateService questionCreateService;

    Question question;
    String writerId = "testId";

    @BeforeEach
    void beforeEach() {
        String title = "제목";
        String content = "내용";
        questionCreateService.createQuestion(writerId, QuestionCreateRequestDto.create(title, content));
        question = questionRepository.findByWriterId(writerId).get(0);
    }

    @Test
    void modify() {
        String mTitle = "modified";
        String mContent = "modified content";
        questionModifyService.modify(writerId, question.getId(), QuestionModifyRequestDto.createForTest(mTitle, mContent));

        // then
        question = questionRepository.findByWriterId(writerId).get(0);
        Assertions.assertEquals(mTitle, question.getTitle());
        Assertions.assertEquals(mContent, question.getContent());
    }

    @Test
    void throwNotYourQuestion() {
        String strangeId = UUID.randomUUID().toString();
        Assertions.assertThrows(
                NotYourQuestionException.class,
                () -> questionModifyService.modify(strangeId, question.getId(), createDto()));
    }

    @Test
    void throwQuestionNotFoundById() {
        String strangeId = UUID.randomUUID().toString();
        Assertions.assertThrows(
                QuestionNotFoundById.class,
                () -> questionModifyService.modify(writerId, strangeId, createDto()));
    }

    @Test
    void throwInvalidResourceConstraintException() {
        InvalidResourceConstraintException e = Assertions.assertThrows(
                InvalidResourceConstraintException.class,
                () -> questionModifyService.modify(writerId, question.getId(), createDto("?".repeat(Question.TITLE_MIN_LENGTH - 1), "?".repeat(Question.CONTENT_MIN_LENGTH)))
        );
        Assertions.assertTrue(e.getErrorMessage().contains(InvalidQuestionTitleException.class.getSimpleName()));

        e  = Assertions.assertThrows(
                InvalidResourceConstraintException.class,
                () -> questionModifyService.modify(writerId, question.getId(), createDto("?".repeat(Question.TITLE_MAX_LENGTH + 1), "?".repeat(Question.CONTENT_MIN_LENGTH)))
        );
        Assertions.assertTrue(e.getErrorMessage().contains(InvalidQuestionTitleException.class.getSimpleName()));

        e = Assertions.assertThrows(
                InvalidResourceConstraintException.class,
                () -> questionModifyService.modify(writerId, question.getId(), createDto("?".repeat(Question.TITLE_MIN_LENGTH), "?".repeat(Question.CONTENT_MIN_LENGTH - 1)))
        );
        Assertions.assertTrue(e.getErrorMessage().contains(InvalidQuestionContentException.class.getSimpleName()));

        e  = Assertions.assertThrows(
                InvalidResourceConstraintException.class,
                () -> questionModifyService.modify(writerId, question.getId(), createDto("?".repeat(Question.TITLE_MIN_LENGTH), "?".repeat(Question.CONTENT_MAX_LENGTH + 1)))
        );
        Assertions.assertTrue(e.getErrorMessage().contains(InvalidQuestionContentException.class.getSimpleName()));
    }

    QuestionModifyRequestDto createDto() {
        return createDto("?".repeat(Question.TITLE_MIN_LENGTH), "?".repeat(Question.CONTENT_MIN_LENGTH));
    }

    QuestionModifyRequestDto createDto(String title, String content) {
        return QuestionModifyRequestDto.createForTest(title, content);
    }
}
