package com.liberty52.auth.service.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Auth {
    @Id
    private String id = UUID.randomUUID().toString();

    @Column(nullable = false, unique = true, updatable = false)
    private String email;

    private String profileUrl;

    private String name;

    @Column(nullable = false, updatable = false)
    private LocalDate createdAt = LocalDate.now();

    private String phoneNumber;

    private String password;

    private Role role;

    @Column(name = "pw_modifiy_request_date")
    private LocalDate passwordModifyRequestDate;

    private String refreshToken;

    @OneToMany(mappedBy = "auth", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<SocialLogin> socialLogins = new ArrayList<>();

    @Builder
    public Auth(String email, String profileUrl, String phoneNumber, Role role) {
        this.email = email;
        this.profileUrl = profileUrl;
        this.phoneNumber = phoneNumber;
        this.role = role;
    }

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

    public void updateRefreshToken(String updateRefreshToken) {
        this.refreshToken = updateRefreshToken;
    }
}
