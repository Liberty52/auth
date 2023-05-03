package com.liberty52.auth.service.applicationservice;

import com.liberty52.auth.service.controller.dto.QuestionReplyCreateRequestDto;

public interface QuestionReplyCreateService {
    void createQuestionReply(String writerId, String role, QuestionReplyCreateRequestDto dto);
}
