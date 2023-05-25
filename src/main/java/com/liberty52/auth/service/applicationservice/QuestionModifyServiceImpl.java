package com.liberty52.auth.service.applicationservice;

import com.liberty52.auth.global.exception.internalservererror.InvalidResourceConstraintException;
import com.liberty52.auth.global.exception.forbidden.NotYourQuestionException;
import com.liberty52.auth.global.exception.notfound.QuestionNotFoundById;
import com.liberty52.auth.global.exception.internal.InvalidQuestionContentException;
import com.liberty52.auth.global.exception.internal.InvalidQuestionTitleException;
import com.liberty52.auth.service.controller.dto.QuestionModifyRequestDto;
import com.liberty52.auth.service.entity.Question;
import com.liberty52.auth.service.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class QuestionModifyServiceImpl implements QuestionModifyService {
    private final QuestionRepository questionRepository;

    @Override
    public void modify(String writerId, String questionId, QuestionModifyRequestDto dto) {
        Question question = questionRepository.findById(questionId).orElseThrow(() -> new QuestionNotFoundById(questionId));
        if(!writerId.equals(question.getWriterId()))
            throw new NotYourQuestionException(writerId);
        try {
            question.modify(dto.getTitle(), dto.getContent());
        } catch (InvalidQuestionContentException | InvalidQuestionTitleException e) {
            throw new InvalidResourceConstraintException(e);
        }
    }
}
