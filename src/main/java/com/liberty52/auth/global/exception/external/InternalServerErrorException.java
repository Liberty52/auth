package com.liberty52.auth.global.exception.external;

public class InternalServerErrorException extends AbstractApiException{
    public InternalServerErrorException(String msg) {
        super(AuthErrorCode.INTERNAL_SERVER_ERROR, msg);
    }
}
