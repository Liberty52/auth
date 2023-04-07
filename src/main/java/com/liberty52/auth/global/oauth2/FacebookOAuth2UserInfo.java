package com.liberty52.auth.global.oauth2;

import java.util.Map;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FacebookOAuth2UserInfo extends OAuth2UserInfo {

    private static final String FACEBOOK_NAME_KEY = "name";
    private static final String FACEBOOK_EMAIL_KEY = "email";
    public FacebookOAuth2UserInfo(Map<String, Object> attributes) {
        super(attributes);
        log.info("attribute = {}", attributes);
    }

    @Override
    public String getId() {
        return null;
    }

    @Override
    public String getPhoneNumber() {
        return null;
    }

    @Override
    public String getImageUrl() {
        return null;
    }

    @Override
    public String getEmail() {
        return (String) attributes.get(FACEBOOK_EMAIL_KEY);
    }

    @Override
    public String getName() {
        return (String) attributes.get(FACEBOOK_NAME_KEY);
    }
}
