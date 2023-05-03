package com.liberty52.auth.service.applicationservice;

import com.liberty52.auth.global.exception.external.NotYourQuestionException;
import com.liberty52.auth.global.exception.external.NotYourRoleException;
import com.liberty52.auth.global.exception.external.QuestionNotFoundById;
import com.liberty52.auth.service.controller.dto.QuestionCreateRequestDto;
import com.liberty52.auth.service.controller.dto.QuestionReplyCreateRequestDto;
import com.liberty52.auth.service.entity.Question;
import com.liberty52.auth.service.entity.QuestionReply;
import com.liberty52.auth.service.entity.QuestionStatus;
import com.liberty52.auth.service.repository.QuestionRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
public class QuestionReplyCreateServiceTest {

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    QuestionReplyCreateService questionReplyCreateService;

    String adminId = "adminId";
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
    void 문의답변추가() {
        String role = "ADMIN";
        QuestionReplyCreateRequestDto dto = QuestionReplyCreateRequestDto.create(questionId, "답변");
        questionReplyCreateService.createQuestionReply(adminId, role, dto);
        Question question = questionRepository.findById(questionId).orElseGet(null);
        QuestionReply questionReply = question.getQuestionReply();

        assertThat(question.getStatus()).isEqualTo(QuestionStatus.DONE);
        assertThat(questionReply.getContent()).isEqualTo("답변");
        assertThat(questionReply.getWriterId()).isEqualTo(adminId);
        assertThat(questionReply.getCreatedAt().toLocalDate()).isEqualTo(LocalDate.now());


        QuestionReplyCreateRequestDto errDto = QuestionReplyCreateRequestDto.create("err", "답변");
        Assertions.assertThrows(NotYourRoleException.class,() -> questionReplyCreateService.createQuestionReply(adminId, "손님", dto));
        Assertions.assertThrows(QuestionNotFoundById.class,() -> questionReplyCreateService.createQuestionReply(adminId, role, errDto));

    }


}
