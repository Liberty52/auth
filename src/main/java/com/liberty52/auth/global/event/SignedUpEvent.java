package com.liberty52.auth.global.event;

public class SignedUpEvent extends SendMailEvent implements Event{

    public SignedUpEvent(String email) {
        super(email, "회원 가입을 축하합니다.");
    }
}
