package com.liberty52.auth.global.exception.external.internalservererror;

public class FileNullException extends S3UploaderException {
    public FileNullException() {
        super("업로드할 파일이 없습니다.");
    }
}
