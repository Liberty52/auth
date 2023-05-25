package com.liberty52.auth.service.applicationservice;

import com.liberty52.auth.global.exception.forbidden.InvalidAdminRoleException;
import com.liberty52.auth.global.exception.notfound.QuestionReplyNotFoundByIdException;
import com.liberty52.auth.service.entity.QuestionReply;
import com.liberty52.auth.service.entity.Role;
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
    public void deleteQuestionReply(String adminId, String role, String questionReplyId) {
        if(!role.equals(Role.ADMIN.name())){
            throw new InvalidAdminRoleException(role);
        }
        QuestionReply questionReply = questionReplyRepository.findById(questionReplyId).orElseThrow(() -> new QuestionReplyNotFoundByIdException(questionReplyId));
        questionReply.removeQuestion();
        questionReplyRepository.delete(questionReply);
    }
}
