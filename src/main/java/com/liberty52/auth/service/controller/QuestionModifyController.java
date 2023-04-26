package com.liberty52.auth.service.controller;

import com.liberty52.auth.service.applicationservice.QuestionCreateService;
import com.liberty52.auth.service.applicationservice.QuestionModifyService;
import com.liberty52.auth.service.controller.dto.QuestionCreateRequestDto;
import com.liberty52.auth.service.controller.dto.QuestionModifyRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class QuestionModifyController {

    private final QuestionModifyService questionModifyService;

    @PutMapping("/questions/{questionId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void questionModify(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String writerId,
            @PathVariable String questionId,
            @Validated @RequestBody QuestionModifyRequestDto dto) {
        questionModifyService.modify(writerId, questionId, dto);
    }
}
