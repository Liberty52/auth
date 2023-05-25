package com.liberty52.auth.service.applicationservice;

import com.liberty52.auth.global.adapter.S3Uploader;
import com.liberty52.auth.global.event.Events;
import com.liberty52.auth.global.event.events.SignedUpEvent;
import com.liberty52.auth.global.exception.badrequest.AuthWithSuchEmailAlreadyExistsException;
import com.liberty52.auth.service.controller.dto.SignUpRequestDto;
import com.liberty52.auth.service.entity.Auth;
import com.liberty52.auth.service.repository.AuthRepository;
import com.liberty52.auth.service.repository.SocialLoginRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class EmailSignUpServiceImpl implements EmailSignUpService {

    private final S3Uploader s3Uploader;
    private final AuthRepository authRepository;
    private final SocialLoginRepository socialLoginRepository;
    private final PasswordEncoder encoder;


    @Override
    public void signUp(SignUpRequestDto dto, MultipartFile imageFile) {
        if (authRepository.findByEmail(dto.getEmail()).isPresent() ||
                socialLoginRepository.findByEmail(dto.getEmail()).isPresent())
            throw new AuthWithSuchEmailAlreadyExistsException();

        String profileImageUrl = uploadImage(imageFile);
        Auth auth = Auth.createUser(dto.getEmail(), encoder.encode(dto.getPassword()),
                dto.getName(), dto.getPhoneNumber(), profileImageUrl);
        authRepository.save(auth);

        Events.raise(new SignedUpEvent(dto.getEmail(), auth.getName()));
    }

    private String uploadImage(MultipartFile multipartFile) {
        if (multipartFile == null)
            return null;
        return s3Uploader.upload(multipartFile);
    }
}
