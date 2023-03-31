package com.liberty52.auth.service.applicationservice;

import static com.liberty52.auth.service.utils.MockConstants.MOCK_USER_EMAIL;
import static org.assertj.core.api.Assertions.*;

import com.liberty52.auth.service.entity.Auth;
import com.liberty52.auth.service.repository.AuthRepository;
import com.liberty52.auth.service.utils.MockFactory;
import com.netflix.discovery.converters.Auto;
import jakarta.persistence.EntityManager;
import java.util.Collections;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
class MemberDeleteServiceSpringBootTest {

    @Autowired
    MemberDeleteService memberDeleteService;

    @Autowired
    AuthRepository authRepository;
    String userId;

    @BeforeEach
    void beforeEach(){
        Auth auth = MockFactory.createMockAuth();
        authRepository.save(auth);

        userId = auth.getId();

        // SET SECURITY
        User user = new User(auth.getEmail(), auth.getPassword(), Collections.EMPTY_LIST);
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user, user.getPassword());
        SecurityContextHolder.getContext().setAuthentication(token);

    }

    @Test
    void deleteByUserId () throws Exception{
        //given
        memberDeleteService.deleteMemberByUserId(userId);
        //when
        Optional<Auth> result_isNotPresent = authRepository.findById(userId);
        //then
        assertThat(result_isNotPresent).isNotPresent();

    }

    @Test
    void deleteByEmail () throws Exception{
        //given
        memberDeleteService.deleteMemberByEmail(MOCK_USER_EMAIL);
        //when
        Optional<Auth> result_isNotPresent = authRepository.findByEmail(MOCK_USER_EMAIL);
        //then
        assertThat(result_isNotPresent).isNotPresent();
    }

    @Test
    void deleteByAuthentication () throws Exception{
        //given
        memberDeleteService.deleteMemberWithToken();
        //when
        Optional<Auth> result_isNotPresent = authRepository.findById(userId);
        //then
        assertThat(result_isNotPresent).isNotPresent();
    }

}
