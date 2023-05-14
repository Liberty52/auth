package com.liberty52.auth.global.exception.external;

public class BadRequestException extends AbstractApiException {
    public BadRequestException(String msg) {
        super(AuthErrorCode.BAD_REQUEST, msg);
    }
}
