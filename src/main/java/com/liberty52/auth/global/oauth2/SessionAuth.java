package com.liberty52.auth.global.oauth2;

import com.liberty52.auth.service.entity.Auth;
import com.liberty52.auth.service.entity.Role;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionAuth implements Serializable {
    private String id;
    private String email;
    private String profileUrl;
    private String phoneNumber;
    private String password;
    private Role role;

    public SessionAuth(Auth auth){
        this.id = auth.getId();
        this.email = auth.getEmail();
        this.profileUrl = auth.getProfileUrl();
        this.phoneNumber = auth.getPhoneNumber();
        this.password = auth.getPassword();
        this.role = auth.getRole();
    }
}