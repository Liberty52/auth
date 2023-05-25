package com.liberty52.auth.global.exception.forbidden;

public class NotYourQuestionException extends NotYourResourceException {
    public NotYourQuestionException(String id) {
        super("Question", id);
    }
}
