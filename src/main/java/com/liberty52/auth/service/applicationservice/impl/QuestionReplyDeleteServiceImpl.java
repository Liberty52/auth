package com.liberty52.auth.service.applicationservice.impl;

import com.liberty52.auth.global.exception.external.notfound.QuestionReplyNotFoundByIdException;
import com.liberty52.auth.global.utils.AdminRoleUtils;
import com.liberty52.auth.service.applicationservice.QuestionReplyDeleteService;
import com.liberty52.auth.service.entity.QuestionReply;
import com.liberty52.auth.service.repository.QuestionReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class QuestionReplyDeleteServiceImpl implements QuestionReplyDeleteService {

  private final QuestionReplyRepository questionReplyRepository;

  @Override
  public void deleteQuestionReplyByAdmin(String adminId, String role, String questionReplyId) {
    AdminRoleUtils.checkRole(role);
    QuestionReply questionReply = questionReplyRepository.findById(questionReplyId)
        .orElseThrow(() -> new QuestionReplyNotFoundByIdException(questionReplyId));
    questionReply.removeQuestion();
    questionReplyRepository.delete(questionReply);
  }
}
