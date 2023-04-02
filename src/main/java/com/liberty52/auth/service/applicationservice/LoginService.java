package com.liberty52.auth.service.applicationservice;

import com.liberty52.auth.service.controller.dto.EmailLoginRequestDto;
import com.liberty52.auth.service.controller.dto.LoginResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public interface LoginService {
    LoginResponseDto login(EmailLoginRequestDto dto, HttpServletResponse response);

    void checkRefreshTokenAndReIssueAccessToken(HttpServletResponse response, String refreshToken);
}
