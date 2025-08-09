package com.gyeryongbrother.pickandtest.member.domain.service.dto;

import com.gyeryongbrother.pickandtest.member.domain.core.Member;
import com.gyeryongbrother.pickandtest.member.domain.core.MemberRole;

public record RegisterMemberResponse(
        Long id,
        MemberRole memberRole,
        String nickname,
        String profileImageUrl
) {

    public static RegisterMemberResponse from(Member member) {
        return new RegisterMemberResponse(
                member.id(),
                member.memberRole(),
                member.nickname(),
                member.profileImageUrl()
        );
    }
}
