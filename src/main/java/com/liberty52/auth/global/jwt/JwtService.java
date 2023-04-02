package com.liberty52.auth.global.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.liberty52.auth.service.repository.AuthRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Getter
@Slf4j
public class JwtService {

  @Value("${jwt.secretKey}")
  private String secretKey;

  @Value("${jwt.access.expiration}")
  private Long accessTokenExpirationPeriod;

  @Value("${jwt.refresh.expiration}")
  private Long refreshTokenExpirationPeriod;

  @Value("${jwt.access.header}")
  private String accessHeader;

  @Value("${jwt.refresh.header}")
  private String refreshHeader;

  private static final String ACCESS_TOKEN_SUBJECT = "AccessToken";
  private static final String REFRESH_TOKEN_SUBJECT = "RefreshToken";
  private static final String AUTH_ID_CLAIM = "authId";
  private static final String BEARER = "Bearer ";

  private final AuthRepository authRepository;

  public String createAccessToken(String id) {
    Date now = new Date();
    return JWT.create()
        .withSubject(ACCESS_TOKEN_SUBJECT)
        .withExpiresAt(new Date(now.getTime() + accessTokenExpirationPeriod))
        .withClaim(AUTH_ID_CLAIM, id)
        .sign(Algorithm.HMAC512(secretKey));
  }

  public String createRefreshToken() {
    Date now = new Date();
    return JWT.create()
        .withSubject(REFRESH_TOKEN_SUBJECT)
        .withExpiresAt(new Date(now.getTime() + refreshTokenExpirationPeriod))
        .sign(Algorithm.HMAC512(secretKey));
  }

  public void sendAccessToken(HttpServletResponse response, String accessToken) {
    response.setStatus(HttpServletResponse.SC_OK);

    response.setHeader(accessHeader, accessToken);
    log.debug("재발급된 Access Token : {}", accessToken);
  }

  public void sendAccessAndRefreshToken(HttpServletResponse response, String accessToken, String refreshToken) {
    response.setStatus(HttpServletResponse.SC_OK);

    setAccessTokenHeader(response, accessToken);
    setRefreshTokenHeader(response, refreshToken);
    log.debug("Access Token, Refresh Token 헤더 설정 완료");
  }

  public Optional<String> extractRefreshToken(HttpServletRequest request) {
    return Optional.ofNullable(request.getHeader(refreshHeader))
        .filter(refreshToken -> refreshToken.startsWith(BEARER))
        .map(refreshToken -> refreshToken.replace(BEARER, ""));
  }

  public Optional<String> extractAccessToken(HttpServletRequest request) {
    return Optional.ofNullable(request.getHeader(accessHeader))
        .filter(token -> token.startsWith(BEARER))
        .map(token -> token.replace(BEARER, ""));
  }

  public Optional<String> extractAuthId(String accessToken) {
    try {
      Optional<String> result = Optional.ofNullable(JWT.require(Algorithm.HMAC512(secretKey))
          .build()
          .verify(accessToken)
          .getClaim(AUTH_ID_CLAIM)
          .asString());

      return result;
    } catch (Exception e) {
      log.error("액세스 토큰이 유효하지 않습니다.");
      return Optional.empty();
    }
  }

  public void setAccessTokenHeader(HttpServletResponse response, String accessToken) {
    response.setHeader(accessHeader, accessToken);
  }

  public void setRefreshTokenHeader(HttpServletResponse response, String refreshToken) {
    response.setHeader(refreshHeader, refreshToken);
  }

  public void updateRefreshToken(String email, String updateRefreshToken) {
    authRepository.findByEmail(email)
        .ifPresentOrElse(
            user -> {
              user.updateRefreshToken(updateRefreshToken);
              authRepository.save(user);
            },
            () -> new Exception("일치하는 회원이 없습니다.")
        );
  }

  public boolean isTokenValid(String token) {
    try {
      JWT.require(Algorithm.HMAC512(secretKey)).build().verify(token);
      return true;
    } catch (Exception e) {
      log.error("유효하지 않은 토큰입니다. {}", e.getMessage());
      return false;
    }
  }
}
