package com.liberty52.auth.service.controller.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class QuestionCreateRequestDto {
    @NotEmpty
    @Size(max = 50)
    String title;

    @NotEmpty
    @Size(max = 10000)
    String content;

    public static QuestionCreateRequestDto create(String title, String content) {
        return new QuestionCreateRequestDto(title, content);
    }
}
