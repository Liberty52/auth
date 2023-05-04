package com.liberty52.auth.global.exception.external;

import com.liberty52.auth.service.entity.QuestionReply;

public class QuestionReplyNotFoundByIdException extends ResourceNotFoundException {
    public QuestionReplyNotFoundByIdException(String questionReplyId) {
        super(QuestionReply.class.getSimpleName(), "id", questionReplyId);
    }
}
