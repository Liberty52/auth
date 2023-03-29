package com.liberty52.auth.service.repository;

import com.liberty52.auth.service.entity.SocialLogin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SocialLoginRepository extends JpaRepository<SocialLogin, Long> {
}

