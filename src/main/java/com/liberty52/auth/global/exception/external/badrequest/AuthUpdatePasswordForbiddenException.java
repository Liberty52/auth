package com.liberty52.auth.global.exception.external.badrequest;

import com.liberty52.auth.global.exception.external.AbstractApiException;
import com.liberty52.auth.global.exception.external.AuthErrorCode;

public class AuthUpdatePasswordForbiddenException extends AbstractApiException {
    public AuthUpdatePasswordForbiddenException() {
        super(AuthErrorCode.UPDATE_PASSWORD_REQUEST_FORBIDDEN);
    }
}
