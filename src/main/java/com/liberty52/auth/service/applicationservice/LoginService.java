package com.liberty52.auth.service.applicationservice;

import com.liberty52.auth.service.controller.dto.EmailLoginRequestDto;
import com.liberty52.auth.service.controller.dto.LoginResponseDto;


public interface LoginService {
    LoginResponseDto login(EmailLoginRequestDto dto);
}
