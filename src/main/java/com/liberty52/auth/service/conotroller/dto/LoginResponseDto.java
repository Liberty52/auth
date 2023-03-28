package com.liberty52.auth.service.conotroller.dto;

import lombok.Getter;

@Getter
public class LoginResponseDto {
    private String accessToken;
    private String refreshToken;
}
