package com.liberty52.auth.global.exception.external;

public class AuthWithSuchEmailAlreadyExistsException extends AbstractApiException {
    public AuthWithSuchEmailAlreadyExistsException() {
        super(AuthErrorCode.EMAIL_ALREADY_EXISTS);
    }
}
