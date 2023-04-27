package com.liberty52.auth.service.applicationservice;

import com.liberty52.auth.service.controller.dto.QuestionDetailResponseDto;
import com.liberty52.auth.service.controller.dto.QuestionRetrieveResponseDto;
import java.util.List;

public interface QuestionRetrieveService {

  List<QuestionRetrieveResponseDto> retrieveQuestions(String writerId, int page, int size);

  QuestionDetailResponseDto retrieveQuestionDetail(String questionId, String writerId);
}
