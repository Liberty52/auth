package com.liberty52.auth.service.controller;

import com.liberty52.auth.service.applicationservice.NoticeModifyService;
import com.liberty52.auth.service.controller.dto.NoticeModifyRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class NoticeModifyController {
    private final NoticeModifyService noticeModifyService;

    @PutMapping("/notices/{noticeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void noticeModify(@RequestHeader("LB-Role") String role,
                             @PathVariable String noticeId,
                             @RequestBody NoticeModifyRequestDto dto) {
        noticeModifyService.modify(role, noticeId, dto);
    }
}
