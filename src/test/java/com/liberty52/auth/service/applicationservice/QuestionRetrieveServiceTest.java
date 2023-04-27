package com.liberty52.auth.service.applicationservice;

import static org.assertj.core.api.Assertions.assertThat;

import com.liberty52.auth.service.controller.dto.QuestionRetrieveResponseDto;
import com.liberty52.auth.service.repository.QuestionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
public class QuestionRetrieveServiceTest {

  private static final String WAITING = "WAITING";
  @Autowired
  QuestionRepository questionRepository;

  @Autowired
  QuestionRetrieveService questionRetrieveService;

  @Test
  void 문의조회() {
    String writerId = "TESTER-001";
    Page<QuestionRetrieveResponseDto> responses =
        questionRetrieveService.retrieveQuestions(writerId, 0, 5);
    for (QuestionRetrieveResponseDto response:responses){
      assertThat(response.getCurrentPage()).isSameAs(1L);
      assertThat(response.getStartPage()).isSameAs(1L);
      assertThat(response.getLastPage()).isSameAs(1L);
      assertThat(response.getStatus()).isEqualTo(WAITING);
      assertThat(response.getTitle()).isEqualTo("this is title");
      assertThat(response.getContent()).isEqualTo("this is content");
    }
  }
}
