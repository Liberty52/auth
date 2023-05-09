package com.liberty52.auth.service.applicationservice;

import com.liberty52.auth.global.exception.external.AuthNotFoundException;
import com.liberty52.auth.global.exception.external.AuthUnauthorizedException;
import com.liberty52.auth.global.exception.external.InvalidTokenException;
import com.liberty52.auth.global.jwt.JwtService;
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
    createTokenToResponse(response, auth);
    return LoginResponseDto.of(auth.getName(), auth.getProfileUrl(), auth.getRole());
  }

  @Override
  public void checkRefreshTokenAndReIssueAccessToken(HttpServletResponse response, String refreshToken) {
    authRepository.findByRefreshToken(refreshToken)
        .ifPresentOrElse(auth -> createTokenToResponse(response, auth), InvalidTokenException::new);
  }

  private void createTokenToResponse(HttpServletResponse response, Auth auth) {
    String accessToken = jwtService.createAccessToken(auth.getId(), auth.getRole());
    String refreshToken = jwtService.createRefreshToken();
    auth.updateRefreshToken(refreshToken);

    response.addHeader("access", "Bearer " + accessToken);
    response.addHeader("refresh", refreshToken);
  }
}

