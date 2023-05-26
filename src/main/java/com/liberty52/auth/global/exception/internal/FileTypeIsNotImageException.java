package com.liberty52.auth.global.exception.internal;

import com.liberty52.auth.global.exception.external.AbstractApiException;
import com.liberty52.auth.global.exception.external.AuthErrorCode;

public class FileTypeIsNotImageException extends AbstractApiException {
    public FileTypeIsNotImageException() {
        super(AuthErrorCode.FILE_TYPE_IS_NOT_IMAGE_ERROR);
    }
}
