package com.liberty52.auth.global.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum AuthErrorCode implements ErrorCode{
    AUTH_NOT_FOUND(HttpStatus.UNAUTHORIZED,"등록되지 않은 계정입니다."),

    AUTH_UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "회원정보가 일치하지 않습니다."),

    EMAIL_ALREADY_EXISTS(HttpStatus.BAD_REQUEST,"이미 존재하는 이메일입니다."),
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED,"토큰이 존재하지 않습니다."),

    INVALID_SOCIAL_LOGIN_CODE_ACCESSED(HttpStatus.BAD_REQUEST,"불가능한 소셜 로그인 코드가 도달했습니다."),

    INVALID_PASSWORD(HttpStatus.BAD_REQUEST,"기존 비밀번호가 일치하지 않습니다"),

    INVALID_ID_OR_INVALID_PHONENUMBER(HttpStatus.BAD_REQUEST,"입력하신 정보와 일치하는 계정이 없습니다."),

    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR,
            "현재 시스템에 오류가 발생하였습니다. 다시 시도해주세요.\n자세한 문의는 고객센터에 문의해주세요."),

    UPDATE_PASSWORD_REQUEST_FORBIDDEN(HttpStatus.FORBIDDEN,
            "해당 링크로는 현재 비밀번호를 변경할 수 없습니다. 다시 메일을 요청해주세요."),

    FILE_CONVERT_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "파일 변환 작업에서 오류가 발생하였습니다."),

    FILE_TYPE_IS_NOT_IMAGE_ERROR(HttpStatus.BAD_REQUEST, "이미지 형식의 파일만 업로드할 수 있습니다."),

    FILE_NULL_ERROR(HttpStatus.BAD_REQUEST, "업로드할 파일이 없습니다."),

    FILE_UPLOAD_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "파일 업로드 작업에 오류가 발생하였습니다."),

    NOT_FOUND(HttpStatus.NOT_FOUND, ""), FORBIDDEN(HttpStatus.FORBIDDEN, ""),

    INVALID_PAGE_NUMBER(HttpStatus.BAD_REQUEST,"유효하지 않은 페이지 번호입니다."),
    INVALID_PAGE_SIZE(HttpStatus.BAD_REQUEST,"유효하지 않은 페이지 크기입니다."), BAD_REQUEST(HttpStatus.BAD_REQUEST, "유효하지 않은 요청입니다.");

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