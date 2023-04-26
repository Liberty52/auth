package com.liberty52.auth.global.exception.external;

public class ForbiddenException extends AbstractApiException {
    public ForbiddenException(String msg) {
        super(AuthErrorCode.FORBIDDEN, msg);
    }
}
