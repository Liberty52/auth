package com.liberty52.auth.global.exception.external;

public class FileConvertException extends AbstractApiException {
    public FileConvertException() {
        super(AuthErrorCode.FILE_CONVERT_ERROR);
    }
}
