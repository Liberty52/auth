package com.liberty52.auth.service.applicationservice;

import com.liberty52.auth.service.controller.dto.ModifyRequestDto;
import com.liberty52.auth.service.controller.dto.ModifyResponseDto;
import org.springframework.web.multipart.MultipartFile;

public interface MemberModifyService {

   ModifyResponseDto getMemberInfoById(String userId);

   void updateMemberInfo(String userId, ModifyRequestDto dto, MultipartFile imageFile);
}
