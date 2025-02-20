package com.gyeryongbrother.pickandtest.member.domain.service.dto;

import com.gyeryongbrother.pickandtest.member.domain.core.Member;
import com.gyeryongbrother.pickandtest.member.domain.core.UserRole;

public record RegisterMemberCommand(
        String name,
        String username,
        String password
) {

    public Member toDomain() {
        return Member.builder()
                .name(name)
                .username(username)
                .password(password)
                .userRole(UserRole.ROLE_USER)
                .build();
    }
}
