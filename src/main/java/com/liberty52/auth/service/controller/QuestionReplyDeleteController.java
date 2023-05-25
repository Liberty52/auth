package com.liberty52.auth.service.controller;

import com.liberty52.auth.service.applicationservice.QuestionReplyDeleteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class QuestionReplyDeleteController {

    private final QuestionReplyDeleteService questionReplyDeleteService;

    @DeleteMapping("/admin/questionReplies/{questionReplyId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteQuestionReplyByAdmin(@RequestHeader(HttpHeaders.AUTHORIZATION) String adminId, @RequestHeader("LB-Role") String role, @PathVariable String questionReplyId) {
        questionReplyDeleteService.deleteQuestionReplyByAdmin(adminId, role, questionReplyId);
    }
}
