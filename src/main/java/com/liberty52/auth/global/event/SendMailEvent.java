package com.liberty52.auth.global.event;

public class SendMailEvent implements Event{

    private String email;
    private String content;

    public SendMailEvent(String email, String content) {
        this.email = email;
        this.content = content;
    }
}
