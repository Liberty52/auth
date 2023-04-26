package com.liberty52.auth.global.exception.external;

public class NotFoundException extends AbstractApiException {
    public NotFoundException(String msg) {
        super(AuthErrorCode.NOT_FOUND, msg);
    }
}
