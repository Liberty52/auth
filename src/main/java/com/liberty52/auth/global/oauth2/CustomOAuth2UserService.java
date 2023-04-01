package com.liberty52.auth.global.oauth2;

import com.liberty52.auth.service.entity.Auth;
import com.liberty52.auth.service.entity.SocialLoginType;
import com.liberty52.auth.service.repository.AuthRepository;
import com.liberty52.auth.service.repository.SocialLoginRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

  private final AuthRepository authRepository;
  private final SocialLoginRepository socialLoginRepository;

  @Override
  public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
    log.debug("CustomOAuth2UserService.loadUser() 실행 - OAuth2 로그인 요청 진입");

    OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
    OAuth2User oAuth2User = delegate.loadUser(userRequest);

    String registrationId = userRequest.getClientRegistration().getRegistrationId();
    SocialLoginType socialType = getSocialType(registrationId);
    String userNameAttributeName = userRequest.getClientRegistration()
        .getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();
    Map<String, Object> attributes = oAuth2User.getAttributes();

    OAuthAttributes extractAttributes = OAuthAttributes.of(socialType, userNameAttributeName, attributes);

    Auth createdUser = getUser(extractAttributes, socialType);

    return new CustomOAuth2User(
        Collections.singleton(new SimpleGrantedAuthority(createdUser.getRole().getKey())),
        attributes,
        extractAttributes.getNameAttributeKey(),
        createdUser.getEmail(),
        createdUser.getRole(),
        createdUser.getId()
    );
  }

  private SocialLoginType getSocialType(String registrationId) {
    return SocialLoginType.NAVER;
  }

  private Auth getUser(OAuthAttributes attributes, SocialLoginType socialType) {
    return authRepository.findByEmail(attributes.getOauth2UserInfo().getEmail()).orElseGet(()-> saveUser(attributes,socialType));
  }

  private Auth saveUser(OAuthAttributes attributes, SocialLoginType socialType) {
    Auth createdUser = authRepository.save(attributes.toAuthEntity(attributes.getOauth2UserInfo()));
    socialLoginRepository.save(attributes.toSocialLoginEntity(createdUser, socialType));

    return createdUser;
  }
}

