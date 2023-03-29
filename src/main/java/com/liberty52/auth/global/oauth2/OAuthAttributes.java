package com.liberty52.auth.global.oauth2;

import com.liberty52.auth.service.entity.Auth;
import com.liberty52.auth.service.entity.Role;
import com.liberty52.auth.service.entity.SocialLogin;
import com.liberty52.auth.service.entity.SocialLoginType;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class OAuthAttributes {

  private String nameAttributeKey; // OAuth2 로그인 진행 시 키가 되는 필드 값, PK와 같은 의미
  private OAuth2UserInfo oauth2UserInfo; // 소셜 타입별 로그인 유저 정보(닉네임, 이메일, 프로필 사진 등등)

  @Builder
  public OAuthAttributes(String nameAttributeKey, OAuth2UserInfo oauth2UserInfo) {
    this.nameAttributeKey = nameAttributeKey;
    this.oauth2UserInfo = oauth2UserInfo;
  }

  public static OAuthAttributes of(SocialLoginType socialType,
      String userNameAttributeName, Map<String, Object> attributes) {

    return ofNaver(userNameAttributeName, attributes);
  }

  public static OAuthAttributes ofNaver(String userNameAttributeName, Map<String, Object> attributes) {
    return OAuthAttributes.builder()
        .nameAttributeKey(userNameAttributeName)
        .oauth2UserInfo(new NaverOAuth2UserInfo(attributes))
        .build();
  }

  public Auth toAuthEntity(OAuth2UserInfo oauth2UserInfo) {
    return Auth.builder()
        .phoneNumber(oauth2UserInfo.getPhoneNumber())
        .email(oauth2UserInfo.getEmail())
        .profileUrl(oauth2UserInfo.getImageUrl())
        .role(Role.GUEST)
        .build();
  }

  public SocialLogin toSocialLoginEntity(Auth auth, SocialLoginType socialLoginType) {
    return SocialLogin.builder().type(socialLoginType).auth(auth).build();
  }
}

