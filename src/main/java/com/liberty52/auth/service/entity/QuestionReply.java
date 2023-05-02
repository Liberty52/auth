package com.liberty52.auth.service.entity;

import com.liberty52.auth.global.exception.internal.InvalidQuestionContentException;
import jakarta.persistence.*;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

public class QuestionReply {

    @Id
    private String id = UUID.randomUUID().toString();
    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;
    @Column(nullable = false)
    private String writerId;
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "question_id")
    private Question question;

    public static final int CONTENT_MAX_LENGTH = 10000;
    public static final int CONTENT_MIN_LENGTH = 1;

    @Builder
    private QuestionReply(String content, String writerId) {
        this.content = content;
        this.writerId = writerId;
    }

    public static QuestionReply create(String content, String writerId) {
        QuestionReply qr = builder().content(content).writerId(writerId).build();
        qr.validContent();
        return qr;
    }

    public void associate(Question question){
        this.question = question;
        question.associate(this);
    }

    private void validContent() {
        if (content.length() > CONTENT_MAX_LENGTH || content.length() < CONTENT_MIN_LENGTH) {
            throw new InvalidQuestionContentException();
        }
    }
}
