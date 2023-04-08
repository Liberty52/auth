package com.liberty52.auth.service.utils;

import static com.liberty52.auth.service.utils.MockConstants.MOCK_PHONE_NUMBER;
import static com.liberty52.auth.service.utils.MockConstants.MOCK_PROFILE_URL;
import static com.liberty52.auth.service.utils.MockConstants.MOCK_USER_EMAIL;
import static com.liberty52.auth.service.utils.MockConstants.MOCK_USER_NAME;
import static com.liberty52.auth.service.utils.MockConstants.MOCK_USER_PASSWORD;

import com.liberty52.auth.service.entity.Auth;
import com.liberty52.auth.service.entity.SocialLogin;
import com.liberty52.auth.service.entity.SocialLoginType;

public class MockFactory {


    public static Auth createMockAuth(){
        return Auth.createUser(MOCK_USER_EMAIL,
                MOCK_USER_PASSWORD,
                MOCK_USER_NAME,
                MOCK_PHONE_NUMBER,
                MOCK_PROFILE_URL);
    }

    public static SocialLogin createSocialLogin(SocialLoginType socialLoginType){
        return SocialLogin.builder()
                .type(socialLoginType)
                .email(MOCK_USER_EMAIL)
                .build();
    }

}
