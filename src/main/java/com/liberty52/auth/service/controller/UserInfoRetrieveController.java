package com.liberty52.auth.service.controller;

import com.liberty52.auth.service.applicationservice.UserInfoRetrieveService;
import com.liberty52.auth.service.controller.dto.UserInfoListResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserInfoRetrieveController {
    private final UserInfoRetrieveService userInfoRetrieveService;

    @GetMapping("/user-info")
    @ResponseStatus(HttpStatus.OK)
    public UserInfoListResponseDto userInfoListByAdmin(@RequestHeader("LB-Role") String role,
                                                       Pageable pageable) {
        return userInfoRetrieveService.retrieveAllByAdmin(role, pageable);
    }
}
