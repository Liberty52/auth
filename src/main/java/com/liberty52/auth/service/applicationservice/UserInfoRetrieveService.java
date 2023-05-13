package com.liberty52.auth.service.applicationservice;

import com.liberty52.auth.service.controller.dto.UserInfoListResponseDto;
import org.springframework.data.domain.Pageable;

public interface UserInfoRetrieveService {
    UserInfoListResponseDto retrieveAllByAdmin(String role, Pageable pageable);
}
