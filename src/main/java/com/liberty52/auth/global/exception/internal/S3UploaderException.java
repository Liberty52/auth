package com.liberty52.auth.global.exception.internal;

import com.liberty52.auth.global.exception.external.AbstractApiException;
import com.liberty52.auth.global.exception.external.AuthErrorCode;

public class S3UploaderException extends AbstractApiException {
    public S3UploaderException() {
        super(AuthErrorCode.FILE_UPLOAD_ERROR);
    }
}
