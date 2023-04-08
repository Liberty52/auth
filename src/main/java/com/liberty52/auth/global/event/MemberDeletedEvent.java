package com.liberty52.auth.global.event;

public class MemberDeletedEvent extends SendMailEvent implements Event{

    public MemberDeletedEvent(String email) {
        super(email, "성공적으로 탈퇴되셨습니다.");
    }

}
