package com.liberty52.auth.service.applicationservice;

import com.liberty52.auth.global.adapter.MailSender;
import com.liberty52.auth.global.contants.MailContentConstants;
import com.liberty52.auth.global.exception.external.AuthNotFoundException;
import com.liberty52.auth.global.exception.external.MailMessagingException;
import com.liberty52.auth.service.entity.Auth;
import com.liberty52.auth.service.repository.AuthRepository;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Base64;

@Slf4j
@Component
@RequiredArgsConstructor
public class PasswordMailServiceImpl implements PasswordMailService {

    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;
    private final MailSender mailSender;
    private final String CONTENT_FORMAT = MailContentConstants.PASSWORD_MAIL_CONTENT_HTML;

    @Value("${liberty52.mail.password-mail-service.protocol}")
    private String protocol;
    @Value("${liberty52.mail.password-mail-service.hostname}")
    private String hostname;
    @Value("${liberty52.mail.password-mail-service.content-href-format}")
    private String contentHrefFormat;
    @Value("${liberty52.mail.password-mail-service.title}")
    private String title;

    @Override
    public boolean sendEmailForUpdatePassword(String email) {
        try {
            Auth auth = authRepository.findByEmail(email).orElseThrow(AuthNotFoundException::new);
            String name = auth.getName();

            String emailToken = getEmailToken(email);
            String limitTime = getLimitTime();
            String content = createMailContent(name, emailToken, limitTime);

            MailSender.Mail mail = MailSender.buildMail(email, title, content, true);

            mailSender.prepareAndSend(mail);

            return true;

        } catch (MessagingException e) {
            throw new MailMessagingException();
        }
    }

    @Override
    @Transactional
    public void updatePassword(String emailToken, String password) {
        String email = new String(Base64.getDecoder().decode(emailToken));
        Auth auth = authRepository.findByEmail(email).orElseThrow(AuthNotFoundException::new);

        String encodedPassword = passwordEncoder.encode(password);
        auth.updatePassword(encodedPassword);
    }

    private String createMailContent(String name, String emailToken, String limitTime) {
        String path = protocol + hostname + contentHrefFormat;
        path = String.format(path, emailToken, limitTime);

        return String.format(CONTENT_FORMAT, name, path);
    }

    private String getEmailToken(String email) {
        return new String(Base64.getEncoder().encode(email.getBytes()));
    }

    private String getLimitTime() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime limitTime = now.plusMinutes(10).truncatedTo(ChronoUnit.SECONDS);
        return limitTime.toString();
    }

}
