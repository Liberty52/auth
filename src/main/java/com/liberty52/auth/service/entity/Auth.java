package com.liberty52.auth.service.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
public class Auth {
    @Id
    private String id = UUID.randomUUID().toString();

    @Column(nullable = false, unique = true, updatable = false)
    private String email;

    private String profileUrl;

    @Column(nullable = false, updatable = false)
    private LocalDate createdAt = LocalDate.now();

    private String phoneNumber;

    private String password;

    @Column(name = "pw_modifiy_request_date")
    private LocalDate passwordModifyRequestDate;

    private String refreshToken;

    @OneToMany(mappedBy = "auth", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<SocialLogin> socialLogins = new ArrayList<>();

    public void addSocialLogin(SocialLogin socialLogin) {
        this.socialLogins.add(socialLogin);
        socialLogin.associate(this);
    }
}
