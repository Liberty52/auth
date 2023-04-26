package com.liberty52.auth.service.applicationservice;

import com.liberty52.auth.service.controller.dto.QuestionCreateRequest;

public interface QuestionCreateService {
    void createQuestion(String writerId, QuestionCreateRequest dto);
}
