package com.liberty52.auth.service.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class LoginRequestDto {
    @NotBlank
    private String email;
    @NotBlank
    private String password;
    @NotNull
    private boolean check;
}
