package com.liberty52.auth.service.applicationservice;


import com.liberty52.auth.service.repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class MemberDeleteServiceImpl implements
        MemberDeleteService {

    private final AuthRepository authRepository;

    @Override
    public void deleteMemberByUserId(String userId) {
        authRepository.deleteById(userId);
    }
}
