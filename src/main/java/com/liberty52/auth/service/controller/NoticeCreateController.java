package com.liberty52.auth.service.controller;

import com.liberty52.auth.service.applicationservice.NoticeCreateService;
import com.liberty52.auth.service.applicationservice.NoticeDeleteService;
import com.liberty52.auth.service.controller.dto.NoticeCreateRequestDto;
import com.liberty52.auth.service.controller.dto.QuestionCreateRequestDto;
import com.liberty52.auth.service.entity.Notice;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class NoticeCreateController {

    private final NoticeCreateService noticeCreateService;

    @PostMapping("/notices")
    @ResponseStatus(HttpStatus.CREATED)
    public void createNotice(@RequestHeader("LB-Role") String role, @Validated @RequestBody NoticeCreateRequestDto dto) {
        noticeCreateService.createNotice(role, dto);
    }
}
