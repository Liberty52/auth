package com.liberty52.auth.global.oauth2;

import java.util.Map;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class KakaoOAuth2UserInfo extends OAuth2UserInfo{

    private final Map<String, Object> kakaoAccount;
    private final Map<String, Object> kakaoProfile;


    public KakaoOAuth2UserInfo(Map<String, Object> attributes) {
        super(attributes);
        // kakao는 kakao_account에 유저정보가 있다. (email)
        kakaoAccount = (Map<String, Object>)attributes.get("kakao_account");
        // kakao_account안에 또 profile이라는 JSON객체가 있다. (nickname, profile_image)
        kakaoProfile = (Map<String, Object>)kakaoAccount.get("profile");
    }

    @Override
    public String getId() {
        return null;
    }

    @Override
    public String getPhoneNumber() {
        return null; // TODO Kakao Sink 검수 완료해야 권한 획득 가능
    }

    @Override
    public String getImageUrl() {
        if(kakaoProfile == null)
            return null;
        return (String) kakaoProfile.get("profile_image_url");
    }

    @Override
    public String getEmail() {
        if(kakaoAccount == null)
            return null;
        return (String) kakaoAccount.get("email");
    }

    @Override
    public String getName() {
        if(kakaoProfile == null)
            return null;
        return (String) kakaoProfile.get("nickname");
    }
}
