package com.liberty52.auth.service.applicationservice;

import com.liberty52.auth.service.entity.Auth;
import com.liberty52.auth.service.repository.AuthRepository;
import com.liberty52.auth.service.utils.MockFactory;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import java.util.Base64;

@SpringBootTest
@ActiveProfiles("local")
class PasswordMailServiceImplTestImpl {


    @Autowired
    private AuthRepository authRepository;
    @Autowired
    private PasswordMailService passwordMailService;
    @Autowired
    private PasswordEncoder passwordEncoder;


    private String email;

    void initAuth(){
        Auth auth = MockFactory.createMockAuth();
        authRepository.save(auth);
        email = auth.getEmail();
    }

    void deleteAuth() {
        Auth auth = authRepository.findByEmail(email).get();
        authRepository.delete(auth);
    }

    @Test
    void test_sendMailForUpdatePassword() throws Exception {
        initAuth();

        Assertions.assertTrue(() -> {
            try {
                return passwordMailService.sendEmailForUpdatePassword(email);
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        });

        deleteAuth();
    }

    @Test
    void test_updatePassword() throws Exception {
        initAuth();

        String emailToken = new String(Base64.getEncoder().encode(email.getBytes()));

        String updatePassword = "test1234";
        passwordMailService.updatePassword(emailToken, updatePassword);

        Auth auth = authRepository.findByEmail(email).get();
        String password = auth.getPassword();

        Assertions.assertAll(
                () -> Assertions.assertNotNull(auth),
                () -> Assertions.assertTrue(passwordEncoder.matches(updatePassword, password))
        );

        deleteAuth();
    }


}