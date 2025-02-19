package com.gyeryongbrother.pickandtest.member.domain.core;

public enum UserRole {
    ROLE_USER, ROLE_ADMIN;

    public static UserRole fromString(String roleName) {
        for (UserRole role : UserRole.values()) {
            if (role.name().equalsIgnoreCase(roleName)) {
                return role;
            }
        }
        throw new IllegalArgumentException("No enum constant for name: " + roleName);
    }
}
