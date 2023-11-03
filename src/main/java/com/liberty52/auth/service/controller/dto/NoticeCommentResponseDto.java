package com.liberty52.auth.service.controller.dto;

import com.liberty52.auth.service.entity.NoticeComment;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class NoticeCommentResponseDto {
    private String commentId;
    private String noticeId;
    private String writerId;
    private String writerName;
    private String writerEmail;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public NoticeCommentResponseDto(NoticeComment resultEntity) {
        this.commentId = resultEntity.getId();
        this.noticeId = resultEntity.getNotice().getId();
        this.writerId = resultEntity.getWriter().getId();
        this.writerName = resultEntity.getWriter().getName();
        this.writerEmail = resultEntity.getWriter().getEmail();
        this.content = resultEntity.getContent();
        this.createdAt = resultEntity.getCreatedAt();
        this.updatedAt = resultEntity.getUpdatedAt();
    }
}
