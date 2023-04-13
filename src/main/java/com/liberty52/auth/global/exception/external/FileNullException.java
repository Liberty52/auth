package com.liberty52.auth.global.exception.external;

public class FileNullException extends AbstractApiException {
    public FileNullException() {
        super(AuthErrorCode.FILE_NULL_ERROR);
    }
}
