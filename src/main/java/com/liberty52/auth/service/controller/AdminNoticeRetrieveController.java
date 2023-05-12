package com.liberty52.auth.service.controller;

import com.liberty52.auth.service.applicationservice.AdminNoticeRetrieveService;
import com.liberty52.auth.service.controller.dto.NoticeDetailResponse;
import com.liberty52.auth.service.controller.dto.NoticeRetrieveResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AdminNoticeRetrieveController {

    private final AdminNoticeRetrieveService adminNoticeRetrieveService;

    @GetMapping("/admin/notices")
    @ResponseStatus(HttpStatus.OK)
    public NoticeRetrieveResponse retrieveNoticesByAdmin(
            @RequestHeader("LB-Role") String role,
            Pageable pageable
    ) {
        return adminNoticeRetrieveService.retrieveNoticesByAdmin(role, pageable);
    }

    @GetMapping("/admin/notices/{noticeId}")
    @ResponseStatus(HttpStatus.OK)
    public NoticeDetailResponse retrieveNoticeDetailByAdmin(
            @RequestHeader("LB-Role") String role,
            @PathVariable String noticeId
    ) {
        return adminNoticeRetrieveService.retrieveNoticeDetailByAdmin(role, noticeId);
    }

}
