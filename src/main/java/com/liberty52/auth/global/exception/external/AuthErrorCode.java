package com.liberty52.auth.global.exception.external;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum AuthErrorCode implements ErrorCode{
    AUTH_NOT_FOUND(HttpStatus.UNAUTHORIZED,"등록되지 않은 계정입니다."),

    EMAIL_ALREADY_EXISTS(HttpStatus.BAD_REQUEST,"이미 존재하는 이메일입니다."),

    INVALID_SOCIAL_LOGIN_CODE_ACCESSED(HttpStatus.INTERNAL_SERVER_ERROR,"불가능한 소셜 로그인 코드가 도달했습니다.")
    ;

    private final HttpStatus httpStatus;
    private final String errorMessage;

    private final String errorCode = "A-" + "0".repeat(Math.max(4-String.valueOf(this.ordinal() + 1).length(), 0)) + (this.ordinal() + 1);

    AuthErrorCode(HttpStatus httpStatus, String errorMessage) {
        this.httpStatus = httpStatus;
        this.errorMessage = errorMessage;
    }

    public String getErrorName() {
        return this.name();
    }
}