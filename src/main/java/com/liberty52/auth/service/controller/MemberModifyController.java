package com.liberty52.auth.service.controller;

import com.liberty52.auth.service.applicationservice.MemberModifyService;
import com.liberty52.auth.service.controller.dto.ModifyRequestDto;
import com.liberty52.auth.service.controller.dto.ModifyResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class MemberModifyController {

  private final MemberModifyService memberModifyService;

  @GetMapping("/modify")
  public @ResponseBody ModifyResponseDto getMemberInfo(@RequestHeader(HttpHeaders.AUTHORIZATION) String userId){
    return null;
  }

  @PutMapping("/modify")
  public void updateMemberInfo(@RequestHeader(HttpHeaders.AUTHORIZATION) String userId,
      @Validated @RequestPart("dto") ModifyRequestDto dto,
      @RequestPart(value = "file", required = false) MultipartFile imageFile){

  }

}
