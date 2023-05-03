package com.liberty52.auth.global.exception.external;

public class NotYourRoleException extends NotYourResourceException {
    public NotYourRoleException(String role) {
        super("Role", role);
    }
}
