package com.liberty52.auth.global.event;

public class SignedUpEvent extends SendMailEvent implements Event{

    public SignedUpEvent(String email, String name) {
        super("[Liberty52] 회원 가입이 되었습니다 ",email, name, "가입 날짜", "회원 가입을 환영합니다!", "당신만의 스피커 Liberty52 제품을 만나보세요!");
    }
}
