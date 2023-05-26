package com.liberty52.auth.global.exception.internal;

import com.liberty52.auth.global.exception.external.AbstractApiException;
import com.liberty52.auth.global.exception.external.AuthErrorCode;

public class FileConvertException extends AbstractApiException {
    public FileConvertException() {
        super(AuthErrorCode.FILE_CONVERT_ERROR);
    }
}
