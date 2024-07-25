package com.gyeryongbrother.pickandtest.member.domain.service.dto;

import com.gyeryongbrother.pickandtest.member.domain.core.Member;

public record RegisterMemberResponse(
        Long id,
        String name
) {

    public static RegisterMemberResponse from(Member member) {
        return new RegisterMemberResponse(
                member.getId(),
                member.getName()
        );
    }
}
