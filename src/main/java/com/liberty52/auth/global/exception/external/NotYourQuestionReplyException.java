package com.liberty52.auth.global.exception.external;

import com.liberty52.auth.service.entity.QuestionReply;

public class NotYourQuestionReplyException extends NotYourResourceException {
    public NotYourQuestionReplyException(String writerId) {
        super(QuestionReply.class.getSimpleName(), writerId);
    }
}
