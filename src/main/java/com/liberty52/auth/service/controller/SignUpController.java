package com.liberty52.auth.service.controller;

import com.liberty52.auth.service.controller.dto.SignUpRequestDto;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignUpController {

    @PostMapping("/sign-up")
    public void signUp(@Validated SignUpRequestDto dto) {
        // signUpService.signUp(dto);
    }
}
