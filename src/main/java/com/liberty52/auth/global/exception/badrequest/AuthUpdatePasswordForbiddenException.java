package com.liberty52.auth.global.exception.badrequest;

import com.liberty52.auth.global.exception.AbstractApiException;
import com.liberty52.auth.global.exception.AuthErrorCode;

public class AuthUpdatePasswordForbiddenException extends AbstractApiException {
    public AuthUpdatePasswordForbiddenException() {
        super(AuthErrorCode.UPDATE_PASSWORD_REQUEST_FORBIDDEN);
    }
}
