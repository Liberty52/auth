package com.liberty52.auth.global.exception.external;

public class MailMessagingException extends AbstractApiException {
    public MailMessagingException() {
        super(AuthErrorCode.INTERNAL_SERVER_ERROR);
    }
}
