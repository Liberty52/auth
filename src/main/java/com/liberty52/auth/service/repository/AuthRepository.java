package com.liberty52.auth.service.repository;

import com.liberty52.auth.service.entity.Auth;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepository extends JpaRepository<Auth, String> {
  Optional<Auth> findByEmail(String email);
  Optional<Auth> findByRefreshToken(String refreshToken);
  Optional<Auth> findByEmailAndPassword(String email, String password);
}
