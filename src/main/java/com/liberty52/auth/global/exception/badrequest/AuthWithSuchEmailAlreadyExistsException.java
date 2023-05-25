package com.liberty52.auth.global.exception.badrequest;

import com.liberty52.auth.global.exception.AbstractApiException;
import com.liberty52.auth.global.exception.AuthErrorCode;

public class AuthWithSuchEmailAlreadyExistsException extends AbstractApiException {
    public AuthWithSuchEmailAlreadyExistsException() {
        super(AuthErrorCode.EMAIL_ALREADY_EXISTS);
    }
}
