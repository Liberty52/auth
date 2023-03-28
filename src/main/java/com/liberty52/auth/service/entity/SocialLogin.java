package com.liberty52.auth.service.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.UUID;

@Entity
@Getter
public class SocialLogin {
    @Id
    private String id = UUID.randomUUID().toString();

    @ManyToOne(optional = false)
    @JoinColumn(name = "auth_id")
    private Auth auth;

    @Enumerated(EnumType.STRING)
    private SocialLoginType type;

    public void associate(Auth auth) {
        this.auth = auth;
    }
}
