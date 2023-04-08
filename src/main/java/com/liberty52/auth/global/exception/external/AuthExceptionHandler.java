package com.liberty52.auth.global.exception.external;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AuthExceptionHandler {

    @ExceptionHandler(AbstractApiException.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(AbstractApiException ex, HttpServletRequest request) {
        return ResponseEntity.status(ex.getHttpStatus())
                .body(ErrorResponse.createErrorResponse(ex, request.getRequestURI()));
    }

    @ExceptionHandler(MessagingException.class)
    public ResponseEntity<ErrorResponse> handleMailMessagingException(MessagingException ex, HttpServletRequest request) {
        ErrorCode errorCode = AuthErrorCode.INTERNAL_SERVER_ERROR;

        return ResponseEntity.status(errorCode.getHttpStatus())
                .body(ErrorResponse.createErrorResponse(errorCode, request.getRequestURI()));
    }

}
