package com.liberty52.auth.service.repository;

import com.liberty52.auth.service.controller.dto.ReviewerProfileResponse;
import com.liberty52.auth.service.entity.Auth;
import com.liberty52.auth.service.entity.Role;
import feign.Param;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AuthRepository extends JpaRepository<Auth, String> {
  Page<Auth> findByRole(Role role, Pageable pageable);
  Optional<Auth> findByEmail(String email);
  Optional<Auth> findByRefreshToken(String refreshToken);
  @Query("SELECT a FROM Auth a WHERE a.name = :name AND a.phoneNumber = :phoneNumber")
  List<Auth> findEmailByNameAndPhoneNumber(@Param String name, @Param String phoneNumber);

  @Query("SELECT a FROM Auth a left join fetch SocialLogin s on a = s.auth "
          + "where a.email = :email")
  Optional<Auth> findAuthAndSocialLoginByEmail(@Param String email);

  @Query("SELECT new com.liberty52.auth.service.controller.dto.ReviewerProfileResponse(a.name, a.profileUrl) from Auth a "
          + "where a.id = :authId")
  Optional<ReviewerProfileResponse> findReviewerProfileById(@Param String authId);
}
