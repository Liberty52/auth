package com.liberty52.auth.service.controller;

import com.liberty52.auth.service.applicationservice.MemberDeleteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class MemberDeleteController {

    private final MemberDeleteService memberDeleteService;

    @DeleteMapping("/member/c1")
    public ResponseEntity<Void> deleteMemberC1(@RequestHeader(HttpHeaders.AUTHORIZATION) String userEmail){
        memberDeleteService.deleteMemberByEmail(userEmail);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/member/c2")
    public ResponseEntity<Void> deleteMemberC2(@RequestHeader(HttpHeaders.AUTHORIZATION) String userId){
        memberDeleteService.deleteMemberByUserId(userId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/member/c3")
    public ResponseEntity<Void> deleteMemberC3(){
        memberDeleteService.deleteMemberWithToken();
        return ResponseEntity.ok().build();
    }


}
