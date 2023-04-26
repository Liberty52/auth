package com.liberty52.auth.service.controller.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class QuestionCreateRequest {
    @NotEmpty
    @Min(1)
    @Max(50)
    String title;

    @NotNull
    @Min(1)
    @Max(10000)
    String content;

    public static QuestionCreateRequest create(String title, String content) {
        return new QuestionCreateRequest(title, content);
    }
}
