package com.liberty52.auth.service.controller;

import com.liberty52.auth.service.applicationservice.LoginService;
import com.liberty52.auth.service.controller.dto.EmailLoginRequestDto;
import com.liberty52.auth.service.controller.dto.LoginResponseDto;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;

    @PostMapping("/login")
    public @ResponseBody LoginResponseDto emailLogin(@Validated @RequestBody EmailLoginRequestDto dto, HttpServletResponse response) {

        return loginService.login(dto, response);
    }

    @GetMapping("/refresh")
    public void checkRefreshTokenAndReIssueAccessToken(HttpServletResponse response, @RequestHeader("X-REFRESHTOKEN") String refreshToken){
        loginService.checkRefreshTokenAndReIssueAccessToken(response,refreshToken);
    }
}
