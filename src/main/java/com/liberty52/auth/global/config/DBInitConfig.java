package com.liberty52.auth.global.config;

import com.liberty52.auth.service.entity.Auth;
import com.liberty52.auth.service.repository.AuthRepository;
import jakarta.annotation.PostConstruct;
import java.lang.reflect.Field;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class DBInitConfig {
    private final DBInitService initService;

    @PostConstruct
    public void init() {
        initService.init();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    public static class DBInitService {

        private final AuthRepository authRepository;
        private final PasswordEncoder encoder;

        public void init() {

            final String password = "12341234";

            try {
                Auth auth = Auth.createUser("test@gmail.com", encoder.encode(password),
                        "김테스터", "010-0101-0101", null);
                Field id = auth.getClass().getDeclaredField("id");

                id.setAccessible(true);
                id.set(auth, "TESTER-001");

                authRepository.save(auth);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
