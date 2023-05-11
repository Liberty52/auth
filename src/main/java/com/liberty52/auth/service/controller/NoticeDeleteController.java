package com.liberty52.auth.service.controller;

import com.liberty52.auth.service.applicationservice.NoticeDeleteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class NoticeDeleteController {

  private final NoticeDeleteService noticeDeleteService;
  @DeleteMapping("/notice/{noticeId}")
  public ResponseEntity<Void> deleteNotice(@RequestHeader("LB-Role") String role, @PathVariable String noticeId) {
    noticeDeleteService.deleteNotice(role, noticeId);
    return ResponseEntity.ok().build();
  }
}