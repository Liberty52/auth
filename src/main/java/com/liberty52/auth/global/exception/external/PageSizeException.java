package com.liberty52.auth.global.exception.external;

public class PageSizeException extends AbstractApiException {

  public PageSizeException() {
    super((AuthErrorCode.INVALID_PAGE_SIZE));
  }
}
