package com.liberty52.auth.service.applicationservice;

import com.liberty52.auth.global.exception.external.NotYourRoleException;
import com.liberty52.auth.global.exception.external.QuestionNotFoundById;
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
    public void createQuestionReply(String writerId, String role, QuestionReplyCreateRequestDto dto) {

        if(!role.equals(Role.ADMIN.name())){
            throw new NotYourRoleException(role);
        }

        Question question = questionRepository.findById(dto.getQuestionId()).orElseThrow(() -> new QuestionNotFoundById(dto.getQuestionId()));
        QuestionReply questionReply = QuestionReply.create(dto.getContent(), writerId);
        questionReply.associate(question);
    }
}
