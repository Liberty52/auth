package com.liberty52.auth.service.controller;


import com.liberty52.auth.service.applicationservice.UserNoticeRetrieveService;
import com.liberty52.auth.service.controller.dto.NoticeDetailResponse;
import com.liberty52.auth.service.controller.dto.NoticeRetrieveResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserNoticeRetrieveController {

    private final UserNoticeRetrieveService userNoticeRetrieveService;


    @GetMapping("/notices")
    public ResponseEntity<NoticeRetrieveResponse> retrieveUserNotice(Pageable pageable){
        NoticeRetrieveResponse response = userNoticeRetrieveService.retrieveUserNotice(
                pageable);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/notices/{noticeId}")
    public ResponseEntity<NoticeDetailResponse> retrieveUserNoticeDetail(@PathVariable("noticeId") String noticeId){
        return ResponseEntity.ok(userNoticeRetrieveService.retrieveUserNoticeDetail(noticeId));
    }

}
