package com.liberty52.auth.global.exception.external;

public class AuthUpdatePasswordForbiddenException extends AbstractApiException {
    public AuthUpdatePasswordForbiddenException() {
        super(AuthErrorCode.UPDATE_PASSWORD_REQUEST_FORBIDDEN);
    }
}
