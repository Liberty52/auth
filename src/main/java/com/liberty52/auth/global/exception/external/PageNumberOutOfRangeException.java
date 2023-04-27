package com.liberty52.auth.global.exception.external;

public class PageNumberOutOfRangeException extends AbstractApiException {

  public PageNumberOutOfRangeException() {
    super((AuthErrorCode.INVALID_PAGE_NUMBER));
  }
}
