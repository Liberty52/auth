package com.liberty52.auth.service.controller.dto;

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
    private String role; //// TODO: 수정해 줘야함

    public static LoginResponseDto of(String access, String refresh, String name, String profileUrl, String role) {
        return new LoginResponseDto(access, refresh, name, profileUrl, role);
    }
}
