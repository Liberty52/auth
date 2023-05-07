package com.liberty52.auth.service.controller;

import com.liberty52.auth.service.applicationservice.QuestionCreateService;
import com.liberty52.auth.service.applicationservice.QuestionReplyCreateService;
import com.liberty52.auth.service.controller.dto.QuestionCreateRequestDto;
import com.liberty52.auth.service.controller.dto.QuestionReplyCreateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class QuestionReplyCreateController {

    private final QuestionReplyCreateService questionReplyCreateService;

    @PostMapping("/questionReplies")
    @ResponseStatus(HttpStatus.CREATED)
    public void createQuestion(@RequestHeader(HttpHeaders.AUTHORIZATION) String adminId, @RequestHeader("LB-Role") String role, @Validated @RequestBody QuestionReplyCreateRequestDto dto) {
        questionReplyCreateService.createQuestionReply(adminId, role, dto);
    }
}
