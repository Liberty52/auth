package com.liberty52.auth.global.exception.external.badrequest;

import com.liberty52.auth.global.exception.external.AbstractApiException;
import com.liberty52.auth.global.exception.external.AuthErrorCode;

public class AuthWithSuchEmailAlreadyExistsException extends AbstractApiException {
    public AuthWithSuchEmailAlreadyExistsException() {
        super(AuthErrorCode.EMAIL_ALREADY_EXISTS);
    }
}
