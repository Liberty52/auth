package com.liberty52.auth.service.applicationservice;

import com.liberty52.auth.global.exception.external.InvalidAdminRoleException;
import com.liberty52.auth.global.exception.external.NotYourQuestionReplyException;
import com.liberty52.auth.global.exception.external.QuestionReplyNotFoundByIdException;
import com.liberty52.auth.service.controller.dto.QuestionReplyModifyRequestDto;
import com.liberty52.auth.service.entity.QuestionReply;
import com.liberty52.auth.service.entity.Role;
import com.liberty52.auth.service.repository.QuestionReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class QuestionReplyModifyServiceImpl implements QuestionReplyModifyService {
    private final QuestionReplyRepository questionReplyRepository;

    @Override
    public void modify(String writerId, String role, String questionReplyId, QuestionReplyModifyRequestDto dto) {
        if(!Role.ADMIN.name().equals(role))
            throw new InvalidAdminRoleException(role);
        QuestionReply questionReply = questionReplyRepository.findById(questionReplyId)
                .orElseThrow(() -> new QuestionReplyNotFoundByIdException(questionReplyId));
        questionReply.modify(dto.getContent()); // ensure validated
    }
}
