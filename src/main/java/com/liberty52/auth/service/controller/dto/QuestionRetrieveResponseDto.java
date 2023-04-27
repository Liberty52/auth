package com.liberty52.auth.service.controller.dto;

import com.liberty52.auth.service.entity.Question;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class QuestionRetrieveResponseDto {
  private List<QuestionContent> contents;
  private long currentPage;
  private long startPage;
  private long lastPage;

  public QuestionRetrieveResponseDto (Question question,long currentPage,long startPage,long lastPage) {
    contents = new ArrayList<>();
    QuestionContent questionContent = new QuestionContent(
        question.getId(), question.getStatus().name(),question.getTitle()
        ,question.getContent(),question.getWriterId(),question.getCreatedAt().toLocalDate());
    contents.add(questionContent);
    this.currentPage = currentPage;
    this.startPage = startPage;
    this.lastPage = lastPage;
  }

  @Data
  public class QuestionContent{
    private String id;
    private String status;
    private String title;
    private String content;
    private String writerId;
    private LocalDate createdAt;

    public QuestionContent(String id,String status,String title,
        String content,String writerId,LocalDate createAt){
      this.id = id;
      this.status = status;
      this.title = title;
      this.content = content;
      this.writerId = writerId;
      this.createdAt = createAt;
    }
  }
}
