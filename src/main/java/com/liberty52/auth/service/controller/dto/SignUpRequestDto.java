package com.liberty52.auth.service.controller.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
public class SignUpRequestDto {
    @NotBlank
    private String email;
    @NotBlank
    private String password;

    private String phoneNumber;
    private String name;
    private String recommender;

}
