package com.liberty52.auth.global.exception.internal;

import com.liberty52.auth.global.exception.AbstractApiException;
import com.liberty52.auth.global.exception.AuthErrorCode;

public class FileNullException extends AbstractApiException {
    public FileNullException() {
        super(AuthErrorCode.FILE_NULL_ERROR);
    }
}
