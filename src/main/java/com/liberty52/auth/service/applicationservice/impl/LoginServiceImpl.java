package com.liberty52.auth.service.applicationservice.impl;

import com.liberty52.auth.global.exception.notfound.AuthNotFoundException;
import com.liberty52.auth.global.exception.unauthorized.AuthUnauthorizedException;
import com.liberty52.auth.global.exception.unauthorized.InvalidTokenException;
import com.liberty52.auth.global.jwt.JwtService;
import com.liberty52.auth.service.applicationservice.LoginService;
import com.liberty52.auth.service.controller.dto.EmailLoginRequestDto;
import com.liberty52.auth.service.controller.dto.LoginResponseDto;
import com.liberty52.auth.service.entity.Auth;
import com.liberty52.auth.service.repository.AuthRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

  private final AuthRepository authRepository;
  private final JwtService jwtService;
  private final PasswordEncoder encoder;


  @Override
  public LoginResponseDto login(EmailLoginRequestDto dto, HttpServletResponse response) {
    Auth auth = authRepository.findByEmail(dto.getEmail()).orElseThrow(AuthNotFoundException::new);
    if (!encoder.matches(dto.getPassword(), auth.getPassword())) {
      throw new AuthUnauthorizedException();
    }
    String refreshToken = jwtService.createTokensAndAddHeaders(auth, true, response);
    auth.updateRefreshToken(refreshToken);
    return LoginResponseDto.of(auth.getName(), auth.getProfileUrl(), auth.getRole());
  }

  @Override
  public void checkRefreshTokenAndReIssueAccessToken(HttpServletResponse response, String reqRefreshToken) {
    Auth auth = authRepository.findByRefreshToken(reqRefreshToken)
        .orElseThrow(InvalidTokenException::new);
    String refreshToken = jwtService.createTokensAndAddHeaders(auth, true, response);
    auth.updateRefreshToken(refreshToken);
  }

}
