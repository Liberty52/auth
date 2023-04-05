package com.liberty52.auth.service.applicationservice;

import com.liberty52.auth.global.adapter.S3Uploader;
import com.liberty52.auth.global.exception.external.AuthNotFoundException;
import com.liberty52.auth.global.exception.external.AuthWithInvalidPasswordException;
import com.liberty52.auth.service.controller.dto.ModifyRequestDto;
import com.liberty52.auth.service.controller.dto.ModifyResponseDto;
import com.liberty52.auth.service.entity.Auth;
import com.liberty52.auth.service.repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberModifyServiceImpl implements MemberModifyService{
  private final S3Uploader s3Uploader;
  private final AuthRepository authRepository;
  private final PasswordEncoder encoder;

  @Override
  public ModifyResponseDto getMemberInfoById(String userId) {
    Auth auth = authRepository.findById(userId).orElseThrow(AuthNotFoundException::new);
    return ModifyResponseDto.builder()
        .email(auth.getEmail())
        .phoneNumber(auth.getPhoneNumber())
        .name(auth.getName())
        .profileUrl(auth.getProfileUrl())
        .build();
  }

  @Override
  public void updateMemberInfo(String userId, ModifyRequestDto dto, MultipartFile imageFile) {
    Auth auth = authRepository.findById(userId).orElseThrow(AuthNotFoundException::new);
    if (!encoder.matches(dto.getOriginPassword(), auth.getPassword())) {
      throw new AuthWithInvalidPasswordException();
    }
    String profileImageUrl = uploadImage(imageFile);
    auth.updateUser(encoder.encode(dto.getUpdatePassword()),dto.getPhoneNumber(),dto.getName(),profileImageUrl);
    authRepository.saveAndFlush(auth);
  }

  private String uploadImage(MultipartFile multipartFile) {
    if(multipartFile == null) {
      return null;
    }
    return s3Uploader.upload(multipartFile);
  }
}
