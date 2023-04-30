package com.liberty52.auth.global.event.events;

import static com.liberty52.auth.global.contants.EmailConstants.DeleteMemberEmailConstants.*;

import com.liberty52.auth.global.event.Event;

public class MemberDeletedEvent extends SendMailEvent implements Event {

    private String authId;

    public MemberDeletedEvent(String email, String name, String authId) {
        super(DELETE_MEMBER_TITLE, email, name,DELETE_MEMBER_DATE_TEXT ,DELETE_MEMBER_MAIN_TEXT , DELETE_MEMBER_SUB_TEXT);
        this.authId = authId;
    }

    public String getAuthId() {
        return authId;
    }
}
