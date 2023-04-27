package com.liberty52.auth.service.applicationservice;

import static org.assertj.core.api.Assertions.assertThat;

import com.liberty52.auth.service.controller.dto.QuestionDetailResponseDto;
import com.liberty52.auth.service.controller.dto.QuestionRetrieveResponseDto;
import com.liberty52.auth.service.controller.dto.QuestionRetrieveResponseDto.QuestionContent;
import com.liberty52.auth.service.repository.QuestionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
public class QuestionRetrieveServiceTest {

  private static final String WAITING = "WAITING";
  private static final String writerId = "TESTER-001";
  private static final String questionedId = "QUESTION-001";
  @Autowired
  QuestionRepository questionRepository;

  @Autowired
  QuestionRetrieveService questionRetrieveService;

  @Test
  void 문의조회() {
    QuestionRetrieveResponseDto response = questionRetrieveService.retrieveQuestions(writerId, 0, 5);
      assertThat(response.getCurrentPage()).isSameAs(1L);
      assertThat(response.getStartPage()).isSameAs(1L);
      assertThat(response.getLastPage()).isSameAs(1L);

      QuestionContent questionContent = response.getContents().get(0);
      assertThat(questionContent.getStatus()).isEqualTo(WAITING);
      assertThat(questionContent.getTitle()).isEqualTo("this is title");
      assertThat(questionContent.getContent()).isEqualTo("this is content");
  }

  @Test
  void 상세문의조회() {
    QuestionDetailResponseDto response = questionRetrieveService.retrieveQuestionDetail(questionedId, writerId);
    assertThat(response.getId()).isEqualTo(questionedId);
    assertThat(response.getWriterId()).isEqualTo(writerId);
    assertThat(response.getTitle()).isEqualTo("this is title");
    assertThat(response.getContent()).isEqualTo("this is content");
    assertThat(response.getStatus()).isEqualTo(WAITING);
  }
}
