package com.liberty52.auth.service.repository;

import static com.liberty52.auth.service.utils.MockConstants.MOCK_USER_EMAIL;
import static org.assertj.core.api.Assertions.*;


import com.liberty52.auth.service.entity.Auth;
import com.liberty52.auth.service.utils.MockFactory;
import com.netflix.discovery.converters.Auto;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class AuthRepositoryTest {

    @Autowired
    AuthRepository authRepository;

    @BeforeEach
    void beforeEach(){
        Auth auth = MockFactory.createMockAuth();
        authRepository.save(auth);
    }

    @Test
    void deleteByEmailTest () throws Exception{
        //given
        authRepository.deleteByEmail(MOCK_USER_EMAIL);
        //when
        Optional<Auth> result_isNotPresent = authRepository.findByEmail(MOCK_USER_EMAIL);
        //then
        assertThat(result_isNotPresent).isNotPresent();
    }

}