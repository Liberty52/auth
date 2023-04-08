package com.liberty52.auth.global.event.events;

import static com.liberty52.auth.global.contants.EmailConstants.DeleteMemberEmailConstants.*;

import com.liberty52.auth.global.event.Event;

public class MemberDeletedEvent extends SendMailEvent implements Event {

    public MemberDeletedEvent(String email, String name) {
        super(DELETE_MEMBER_TITLE, email, name,DELETE_MEMBER_DATE_TEXT ,DELETE_MEMBER_MAIN_TEXT , DELETE_MEMBER_SUB_TEXT);
    }

}
