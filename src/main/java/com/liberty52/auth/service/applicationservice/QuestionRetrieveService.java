package com.liberty52.auth.service.applicationservice;

import com.liberty52.auth.service.controller.dto.QuestionRetrieveResponseDto;
import org.springframework.data.domain.Page;

public interface QuestionRetrieveService {

  Page<QuestionRetrieveResponseDto> retrieveQuestions(String writerId, int page, int size);
}
