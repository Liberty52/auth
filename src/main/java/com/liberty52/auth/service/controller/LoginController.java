package com.liberty52.auth.service.controller;

import com.liberty52.auth.service.applicationservice.LoginService;
import com.liberty52.auth.service.controller.dto.EmailLoginRequestDto;
import com.liberty52.auth.service.controller.dto.LoginRequestDto;
import com.liberty52.auth.service.controller.dto.LoginResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;

    @PostMapping("/login")
    public LoginResponseDto EmailLogin(@Validated @RequestBody EmailLoginRequestDto dto) {
        return loginService.login(dto);
    }
}
