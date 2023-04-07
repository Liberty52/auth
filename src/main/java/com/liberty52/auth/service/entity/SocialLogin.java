package com.liberty52.auth.service.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class SocialLogin {
    @Id
    private String id = UUID.randomUUID().toString();

    @ManyToOne(optional = false)
    @JoinColumn(name = "auth_id")
    private Auth auth;

    @Enumerated(EnumType.STRING)
    private SocialLoginType type;

    @Column(nullable = false, updatable = false)
    private String email;

    @Builder
    public SocialLogin(Auth auth, SocialLoginType type, String email) {
        id = UUID.randomUUID().toString();
        this.auth = auth;
        this.type = type;
        this.email = email;
    }

    public void associate(Auth auth) {
        this.auth = auth;
    }
}
