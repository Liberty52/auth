package com.liberty52.auth.service.controller.dto;

import com.liberty52.auth.service.entity.Role;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class LoginResponseDto {
    private String accessToken;
    private String refreshToken;
    private String name;
    private String profileUrl;
    private Role role;

    public static LoginResponseDto of(String access, String refresh, String name, String profileUrl, Role role) {
        return new LoginResponseDto(access, refresh, name, profileUrl, role);
    }
}
