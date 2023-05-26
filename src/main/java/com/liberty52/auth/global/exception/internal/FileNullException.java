package com.liberty52.auth.global.exception.internal;

import com.liberty52.auth.global.exception.external.AbstractApiException;
import com.liberty52.auth.global.exception.external.AuthErrorCode;

public class FileNullException extends AbstractApiException {
    public FileNullException() {
        super(AuthErrorCode.FILE_NULL_ERROR);
    }
}
