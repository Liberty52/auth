package com.liberty52.auth.service.repository;

import com.liberty52.auth.service.entity.Auth;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthRepository extends JpaRepository<Auth, String> {
    Optional<Auth> findByEmail(String email);
    void deleteByEmail(String email);
}