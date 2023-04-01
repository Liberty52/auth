package com.liberty52.auth.service.controller;

import com.liberty52.auth.service.applicationservice.LoginService;
import com.liberty52.auth.service.controller.dto.EmailLoginRequestDto;
import com.liberty52.auth.service.controller.dto.LoginRequestDto;
import com.liberty52.auth.service.controller.dto.LoginResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;

//    @PostMapping("/login")
//    public LoginResponseDto login(@Validated LoginRequestDto dto) {
//        // return loginService.login(token, dto);
//        return null;
//    }

    @PostMapping("/login")
    public LoginResponseDto EmailLogin(@Validated @RequestPart("dto") EmailLoginRequestDto dto) {
        return loginService.login(dto);
    }
}
