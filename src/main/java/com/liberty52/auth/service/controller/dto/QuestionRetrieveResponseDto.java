package com.liberty52.auth.service.controller.dto;

import com.liberty52.auth.service.entity.Question;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class QuestionRetrieveResponseDto {

  private String id;
  private String status;
  private String title;
  private String content;
  private String writerId;
  private LocalDate createdAt;
  private long currentPage;
  private long startPage;
  private long lastPage;

  public static QuestionRetrieveResponseDto create(Question question,long currentPage,long startPage,long lastPage) {
    return  QuestionRetrieveResponseDto.builder()
        .id(question.getId())
        .status(question.getStatus().name())
        .title(question.getTitle())
        .content(question.getContent())
        .writerId(question.getWriterId())
        .createdAt(question.getCreatedAt().toLocalDate())
        .currentPage(currentPage)
        .startPage(startPage)
        .lastPage(lastPage)
        .build();
  }
}
