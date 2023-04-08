package com.liberty52.auth.service.controller;

import com.liberty52.auth.service.applicationservice.PasswordMailService;
import com.liberty52.auth.service.controller.dto.EmailDto;
import com.liberty52.auth.service.controller.dto.PasswordUpdateRequestDto;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/password")
@RequiredArgsConstructor
public class PasswordMailController {

    private final PasswordMailService passwordMailService;

    /**
     * 이메일로 비밀번호 찾기.
     * 요청된 이메일로 비밀번호 변경 링크를 전송.
     *
     * @param dto 비밀번호 변경할 이메일
     * @throws MessagingException
     */
    @PostMapping("/send-mail")
    @ResponseStatus(HttpStatus.OK)
    public void sendEmailForUpdatePassword(@RequestBody EmailDto dto) {
        passwordMailService.sendEmailForUpdatePassword(dto.getEmail());
    }

    /**
     * 메일 링크에 의한 비밀번호 변경.
     *
     * @param emailToken 요청 유저를 확인, 인증하기 위해 유저의 이메일을 Base64로 인코딩한 문자열
     * @param dto 변경할 비밀번호
     */
    @PatchMapping
    @ResponseStatus(HttpStatus.OK)
    public void updatePassword(
            @RequestParam("emailToken") String emailToken,
            @RequestBody PasswordUpdateRequestDto dto
    ) {
        passwordMailService.updatePassword(emailToken, dto.getPassword());
    }

}
