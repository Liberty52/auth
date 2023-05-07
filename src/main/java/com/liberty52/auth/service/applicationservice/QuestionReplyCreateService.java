package com.liberty52.auth.service.applicationservice;

import com.liberty52.auth.service.controller.dto.QuestionReplyCreateRequestDto;

public interface QuestionReplyCreateService {
    void createQuestionReply(String adminId, String role, QuestionReplyCreateRequestDto dto);
}
