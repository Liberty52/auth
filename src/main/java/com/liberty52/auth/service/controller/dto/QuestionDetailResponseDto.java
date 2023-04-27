package com.liberty52.auth.service.controller.dto;

import com.liberty52.auth.service.entity.Question;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class QuestionDetailResponseDto {
  private String id;
  private String title;
  private String content;
  private String status;
  private String writerId;
  private LocalDate createdAt;

  public static QuestionDetailResponseDto create(Question question) {
    return QuestionDetailResponseDto.builder()
        .id(question.getId())
        .title(question.getTitle())
        .content(question.getContent())
        .status(question.getStatus().name())
        .writerId(question.getWriterId())
        .createdAt(question.getCreatedAt().toLocalDate())
        .build();
  }
}
