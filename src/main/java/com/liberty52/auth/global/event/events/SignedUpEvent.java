package com.liberty52.auth.global.event.events;

import static com.liberty52.auth.global.constants.EmailConstants.SignUpEmailConstants.*;

import com.liberty52.auth.global.event.Event;

public class SignedUpEvent extends SendMailEvent implements Event {

    public SignedUpEvent(String email, String name) {
        super(SIGN_UP_TITLE,email, name, SIGN_UP_DATE_TEXT, SIGN_UP_MAIN_TEXT,SIGN_UP_SUB_TEXT );
    }
}
