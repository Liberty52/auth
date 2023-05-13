package com.liberty52.auth.service.controller.dto;

import com.liberty52.auth.service.entity.Auth;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class UserInfoResponseDto {
    private String id;
    private String name;
    private String phoneNumber;
    private LocalDate createdAt;
    private String role;

    public static UserInfoResponseDto of(Auth auth) {
        return builder()
                .id(auth.getId())
                .name(auth.getName())
                .phoneNumber(auth.getPhoneNumber())
                .createdAt(auth.getCreatedAt())
                .role(auth.getRole().name())
                .build();
    }

    public static UserInfoResponseDto of(String id, String name, String phoneNumber, LocalDate createdAt, String role) {
        return builder()
                .id(id)
                .name(name)
                .phoneNumber(phoneNumber)
                .createdAt(createdAt)
                .role(role)
                .build();
    }
}
