package com.liberty52.auth.global.exception.internal;

import com.liberty52.auth.global.exception.AbstractApiException;
import com.liberty52.auth.global.exception.AuthErrorCode;

public class MailMessagingException extends AbstractApiException {
    public MailMessagingException() {
        super(AuthErrorCode.INTERNAL_SERVER_ERROR);
    }
}
