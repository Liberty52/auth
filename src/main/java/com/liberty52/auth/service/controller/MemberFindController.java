package com.liberty52.auth.service.controller;

import com.liberty52.auth.service.applicationservice.MemberFindService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberFindController {
  private final MemberFindService memberFindService;

  public void findEmailByPhoneNumberAndName(String phoneNumber,String name){

  }
}
