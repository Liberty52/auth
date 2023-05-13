package com.liberty52.auth.global.utils;

import com.liberty52.auth.global.exception.external.InvalidAdminRoleException;

import static com.liberty52.auth.service.entity.Role.ADMIN;

public class AdminRoleUtils {
    public static void checkRole(String role) {
        if (!ADMIN.name().equals(role)) {
            throw new InvalidAdminRoleException(role);
        }
    }
}