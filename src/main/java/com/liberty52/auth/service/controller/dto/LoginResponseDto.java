package com.liberty52.auth.service.controller.dto;

import lombok.Getter;

@Getter
public class LoginResponseDto {
    private String accessToken;
    private String refreshToken;
}
