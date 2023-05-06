package com.liberty52.auth.service.controller;

import com.liberty52.auth.service.applicationservice.QuestionReplyModifyService;
import com.liberty52.auth.service.controller.dto.QuestionReplyCreateRequestDto;
import com.liberty52.auth.service.controller.dto.QuestionReplyModifyRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class QuestionReplyModifyController {

    private final QuestionReplyModifyService questionReplyModifyService;

    @PutMapping("/questionReplies/{questionReplyId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void questionReplyModify(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String writerId,
            @RequestHeader("X_Role") String role,
            @PathVariable String questionReplyId,
            @Validated @RequestBody QuestionReplyModifyRequestDto dto) {
        questionReplyModifyService.modify(writerId, role, questionReplyId, dto);
    }
}