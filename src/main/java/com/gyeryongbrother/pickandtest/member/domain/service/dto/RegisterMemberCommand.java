package com.gyeryongbrother.pickandtest.member.domain.service.dto;

import com.gyeryongbrother.pickandtest.member.domain.core.Member;

public record RegisterMemberCommand(
        String name
) {

    public Member toDomain() {
        return Member.builder()
                .name(name)
                .build();
    }
}
