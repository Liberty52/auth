package com.liberty52.auth.service.applicationservice;

import com.liberty52.auth.global.exception.external.AuthNotFoundException;
import com.liberty52.auth.service.controller.dto.ModifyRequestDto;
import com.liberty52.auth.service.controller.dto.ModifyResponseDto;
import com.liberty52.auth.service.entity.Auth;
import com.liberty52.auth.service.repository.AuthRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MemberModifyServiceImpl implements MemberModifyService{

  private final AuthRepository authRepository;

  @Override
  public ModifyResponseDto getMemberInfoById(String userId) {
    Auth auth = authRepository.findById(userId).orElseThrow(AuthNotFoundException::new);
    return ModifyResponseDto.builder()
        .email(auth.getEmail())
        .phoneNumber(auth.getPhoneNumber())
        .name(auth.getName())
        .profileUrl(auth.getProfileUrl()) //TODO :Profile 관련 수정예정
        .build();
  }

  @Override
  public void updateMemberInfo(String userId, ModifyRequestDto dto) {

  }
}
