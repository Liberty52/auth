package com.liberty52.auth.global.exception.internal;

import com.liberty52.auth.global.exception.AbstractApiException;
import com.liberty52.auth.global.exception.AuthErrorCode;

public class S3UploaderException extends AbstractApiException {
    public S3UploaderException() {
        super(AuthErrorCode.FILE_UPLOAD_ERROR);
    }
}
