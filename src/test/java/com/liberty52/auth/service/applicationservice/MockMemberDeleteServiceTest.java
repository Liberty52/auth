package com.liberty52.auth.service.applicationservice;

import static com.liberty52.auth.service.utils.MockConstants.MOCK_USER_EMAIL;
import static com.liberty52.auth.service.utils.MockFactory.createMockAuth;
import static org.assertj.core.api.Assertions.*;

import com.liberty52.auth.service.entity.Auth;
import com.liberty52.auth.service.fake.FakeAuthRepository;
import com.liberty52.auth.service.fake.FakeMemberDeleteService;
import com.liberty52.auth.service.repository.AuthRepository;
import java.util.Collections;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

class MockMemberDeleteServiceTest {

    AuthRepository authRepository;
    MemberDeleteService memberDeleteService;

    String mockUserId;

    @BeforeEach
    void setMockUserData(){
        authRepository = new FakeAuthRepository();
        memberDeleteService = new FakeMemberDeleteService(authRepository);

        // save Auth Entity
        Auth auth = createMockAuth();
        mockUserId = authRepository.save(auth).getId();


        // SET SECURITY
        User user = new User(auth.getEmail(), auth.getPassword(), Collections.EMPTY_LIST);
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user, user.getPassword());
        SecurityContextHolder.getContext().setAuthentication(token);


    }

    // Controller에서 UserId를 받았을 때 삭제
    @Test
    void deleteByUserId  () throws Exception{
        //given
        memberDeleteService.deleteMemberByUserId(mockUserId);
        //when
        Optional<Auth> result = authRepository.findById(mockUserId);
        //then
        assertThat(result).isNotPresent();
    }

    // Controller에서 이메일을 받았을 때 삭제
    @Test
    void deleteByUserMail () throws Exception{
        //given
        memberDeleteService.deleteMemberByEmail(MOCK_USER_EMAIL);
        //when
        Optional<Auth> result = authRepository.findById(MOCK_USER_EMAIL);
        //then
        assertThat(result).isNotPresent();
    
    }

    // SecurityContext에서 토큰을 파싱하여 데이터를 물고있을때
    @Test
    void deleteByAuthentication () throws Exception{
        //given
        memberDeleteService.deleteMemberWithToken();
        //when
        Optional<Auth> result = authRepository.findById(mockUserId);
        //then
        assertThat(result).isNotPresent();
    }
}