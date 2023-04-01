package com.liberty52.auth.service.applicationservice;

import com.liberty52.auth.global.exception.internal.AuthNotFoundException;
import com.liberty52.auth.service.controller.dto.EmailLoginRequestDto;
import com.liberty52.auth.service.controller.dto.LoginResponseDto;
import com.liberty52.auth.service.entity.Auth;
import com.liberty52.auth.service.repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService{

    private final AuthRepository authRepository;

    @Override
    public LoginResponseDto login(EmailLoginRequestDto dto) {
        Auth auth = authRepository.findByEmailAndPassword(dto.getEmail(), dto.getPassword()).orElseThrow(AuthNotFoundException::new);

        return LoginResponseDto.of("access", "refresh", auth.getName(), auth.getProfileUrl(), "role");
    }
}
