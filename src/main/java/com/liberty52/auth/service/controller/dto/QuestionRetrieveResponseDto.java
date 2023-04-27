package com.liberty52.auth.service.controller.dto;

import com.liberty52.auth.service.entity.Question;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.springframework.data.domain.Page;

@Getter
@AllArgsConstructor
public class QuestionRetrieveResponseDto {
  private List<QuestionContent> contents;
  private long currentPage;
  private long startPage;
  private long lastPage;

  public QuestionRetrieveResponseDto (Page<Question> questionList,long currentPage,long startPage,long lastPage) {
    contents = questionList.stream().map(q-> new QuestionContent(q.getId(),q.getStatus().name(),
        q.getTitle(),q.getContent(),q.getWriterId(),q.getCreatedAt().toLocalDate())).toList();

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
