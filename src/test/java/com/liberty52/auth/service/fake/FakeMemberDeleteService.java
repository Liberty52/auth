package com.liberty52.auth.service.fake;

import com.liberty52.auth.service.applicationservice.MemberDeleteService;
import com.liberty52.auth.service.repository.AuthRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

public class FakeMemberDeleteService implements MemberDeleteService {

    private final AuthRepository authRepository;

    public FakeMemberDeleteService(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    @Override
    public void deleteMemberByUserId(String userId) {
        authRepository.deleteById(userId);
    }

    @Override
    public void deleteMemberByEmail(String email) {
        authRepository.deleteByEmail(email);

    }

    @Override
    public void deleteMemberWithToken() {
        authRepository.deleteByEmail(extractUserFromAuthentication().getUsername());
    }

    private User extractUserFromAuthentication() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}