package com.liberty52.auth.service.applicationservice;

import com.liberty52.auth.global.exception.forbidden.InvalidAdminRoleException;
import com.liberty52.auth.global.exception.notfound.QuestionNotFoundById;
import com.liberty52.auth.service.controller.dto.QuestionReplyCreateRequestDto;
import com.liberty52.auth.service.entity.Question;
import com.liberty52.auth.service.entity.QuestionReply;
import com.liberty52.auth.service.entity.Role;
import com.liberty52.auth.service.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class QuestionReplyCreateServiceImpl implements QuestionReplyCreateService {

    private final QuestionRepository questionRepository;

    @Override
    public void createQuestionReply(String adminId, String role, QuestionReplyCreateRequestDto dto) {

        if(!role.equals(Role.ADMIN.name())){
            throw new InvalidAdminRoleException(role);
        }

        Question question = questionRepository.findById(dto.getQuestionId()).orElseThrow(() -> new QuestionNotFoundById(dto.getQuestionId()));
        QuestionReply questionReply = QuestionReply.create(dto.getContent(), adminId);
        questionReply.associate(question);
    }
}
