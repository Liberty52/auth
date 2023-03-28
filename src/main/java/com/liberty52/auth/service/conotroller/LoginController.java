package com.liberty52.auth.service.conotroller;

import com.liberty52.auth.service.conotroller.dto.LoginRequestDto;
import com.liberty52.auth.service.conotroller.dto.LoginResponseDto;
import com.liberty52.auth.service.conotroller.dto.SignUpRequestDto;
import org.springframework.http.HttpHeaders;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @PostMapping("/login")
    public LoginResponseDto login(@Validated LoginRequestDto dto) {
        // return loginService.login(token, dto);
        return null;
    }
}
