package com.liberty52.auth.service.applicationservice;

import jakarta.mail.MessagingException;

public interface PasswordMailService {

    boolean sendEmailForUpdatePassword(String email) throws MessagingException;
    void updatePassword(String emailToken, String password);

}
