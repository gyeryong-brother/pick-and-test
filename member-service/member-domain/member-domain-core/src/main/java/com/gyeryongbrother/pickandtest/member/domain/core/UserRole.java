package com.gyeryongbrother.pickandtest.member.domain.core;

import static com.gyeryongbrother.pickandtest.member.domain.core.exception.MemberCoreExceptionType.INVALID_USERROLE;

import com.gyeryongbrother.pickandtest.member.domain.core.exception.MemberCoreException;
import java.util.Arrays;

public enum UserRole {
    ROLE_USER,
    ROLE_ADMIN,
    ;

    public static UserRole fromString(String roleName) {
        return Arrays.stream(UserRole.values())
                .filter(role -> role.name().equalsIgnoreCase(roleName))
                .findFirst()
                .orElseThrow(() -> new MemberCoreException(
                        INVALID_USERROLE));  // 또는 적절한 기본값 예: .orElseThrow(() -> new IllegalArgumentException("Invalid role name"));
    }
}
