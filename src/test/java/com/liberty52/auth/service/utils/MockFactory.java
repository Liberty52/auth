package com.liberty52.auth.service.utils;

import static com.liberty52.auth.service.utils.MockConstants.MOCK_PHONE_NUMBER;
import static com.liberty52.auth.service.utils.MockConstants.MOCK_PROFILE_URL;
import static com.liberty52.auth.service.utils.MockConstants.MOCK_USER_EMAIL;
import static com.liberty52.auth.service.utils.MockConstants.MOCK_USER_NAME;
import static com.liberty52.auth.service.utils.MockConstants.MOCK_USER_PASSWORD;

import com.liberty52.auth.service.entity.Auth;

public class MockFactory {


    public static Auth createMockAuth(){
        return Auth.create(MOCK_USER_EMAIL,
                MOCK_USER_PASSWORD,
                MOCK_USER_NAME,
                MOCK_PHONE_NUMBER,
                MOCK_PROFILE_URL);
    }

}
