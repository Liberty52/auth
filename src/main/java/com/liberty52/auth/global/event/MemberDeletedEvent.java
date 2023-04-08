package com.liberty52.auth.global.event;

import java.time.LocalDate;

public class MemberDeletedEvent extends SendMailEvent implements Event{

    public MemberDeletedEvent(String email, String name) {
        super("[Liberty52] 회원 탈퇴가 완료되었습니다 ", email, name, "탈퇴 날짜", "회원 탈퇴가 완료되었습니다.", "그동안 Liberty52의 서비스를 이용해주셔서 감사합니다.");
    }

}
