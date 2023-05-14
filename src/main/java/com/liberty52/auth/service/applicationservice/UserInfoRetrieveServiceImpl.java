package com.liberty52.auth.service.applicationservice;

import com.liberty52.auth.global.utils.AdminRoleUtils;
import com.liberty52.auth.service.controller.dto.UserInfoListResponseDto;
import com.liberty52.auth.service.entity.Auth;
import com.liberty52.auth.service.repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserInfoRetrieveServiceImpl implements UserInfoRetrieveService {
    private final AuthRepository authRepository;

    @Override
    public UserInfoListResponseDto retrieveAllByAdmin(String role, Pageable pageable) {
        AdminRoleUtils.checkRole(role);
        Page<Auth> page = authRepository.findAll(pageable);
        return UserInfoListResponseDto.of(page);
    }
}
