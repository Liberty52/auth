package com.liberty52.auth.global.exception.external;

public class S3UploaderException extends AbstractApiException {
    public S3UploaderException() {
        super(AuthErrorCode.FILE_UPLOAD_ERROR);
    }
}
