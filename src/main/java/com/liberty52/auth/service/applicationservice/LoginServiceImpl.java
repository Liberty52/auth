package com.liberty52.auth.service.applicationservice;

import com.liberty52.auth.global.exception.internal.AuthNotFoundException;
import com.liberty52.auth.global.jwt.JwtService;
import com.liberty52.auth.service.controller.dto.EmailLoginRequestDto;
import com.liberty52.auth.service.controller.dto.LoginResponseDto;
import com.liberty52.auth.service.entity.Auth;
import com.liberty52.auth.service.repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService{

    private final AuthRepository authRepository;
    private final JwtService jwtService;
    private final PasswordEncoder encoder;


    @Override
    public LoginResponseDto login(EmailLoginRequestDto dto) {
        Auth auth = authRepository.findByEmail(dto.getEmail()).orElseThrow(AuthNotFoundException::new);
        if(!encoder.matches(dto.getPassword(), auth.getPassword()))
            throw new AuthNotFoundException();

        String accessToken = jwtService.createAccessToken(dto.getEmail());
        String refreshToken = jwtService.createRefreshToken();
        auth.updateRefreshToken(refreshToken);
        return LoginResponseDto.of(accessToken, refreshToken, auth.getName(), auth.getProfileUrl(), auth.getRole());
    }
}

