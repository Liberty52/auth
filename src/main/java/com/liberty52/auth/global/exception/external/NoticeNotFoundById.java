package com.liberty52.auth.global.exception.external;

public class NoticeNotFoundById extends ResourceNotFoundException {

    public NoticeNotFoundById(String noticeId) {
        super("Notice", "id", noticeId);
    }
}
