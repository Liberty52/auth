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
    private String name;
    private String profileUrl;
    private String phoneNumber;
    private String password;
    @Column(nullable = false, updatable = false)
    private LocalDate createdAt = LocalDate.now();
    private String refreshToken;
    @Column(name = "pw_modifiy_request_date")
    private LocalDate passwordModifyRequestDate;

    @OneToMany(mappedBy = "auth", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<SocialLogin> socialLogins = new ArrayList<>();

    public static Auth create(String email, String password, String name, String phoneNumber, String profileUrl) {
        Auth auth = new Auth();
        auth.email = email; auth.password = password;
        auth.name = name; auth.phoneNumber = phoneNumber;
        auth.profileUrl = profileUrl;
        return auth;
    }

    public void addSocialLogin(SocialLogin socialLogin) {
        this.socialLogins.add(socialLogin);
        socialLogin.associate(this);
    }
}
