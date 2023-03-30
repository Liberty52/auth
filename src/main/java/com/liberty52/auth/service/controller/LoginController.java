package com.liberty52.auth.service.controller;

import com.liberty52.auth.service.controller.dto.LoginRequestDto;
import com.liberty52.auth.service.controller.dto.LoginResponseDto;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @PostMapping("/login")
    public LoginResponseDto login(@Validated LoginRequestDto dto) {
        // return loginService.login(token, dto);
        return null;
    }
}
