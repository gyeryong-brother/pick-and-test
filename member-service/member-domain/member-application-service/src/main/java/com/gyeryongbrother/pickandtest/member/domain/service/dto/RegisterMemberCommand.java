package com.gyeryongbrother.pickandtest.member.domain.service.dto;

import com.gyeryongbrother.pickandtest.member.domain.core.Member;

public record RegisterMemberCommand(
        String name,
        String userId,
        String password
) {

    public Member toDomain() {
        return Member.builder()
                .name(name)
                .userId(userId)
                .password(password)
                .build();
    }
}
