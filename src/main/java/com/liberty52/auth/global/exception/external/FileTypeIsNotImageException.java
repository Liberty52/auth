package com.liberty52.auth.global.exception.external;

public class FileTypeIsNotImageException extends AbstractApiException {
    public FileTypeIsNotImageException() {
        super(AuthErrorCode.FILE_TYPE_IS_NOT_IMAGE_ERROR);
    }
}
