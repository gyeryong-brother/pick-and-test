package com.gyeryongbrother.pickandtest.member.domain.service.dto;

import com.gyeryongbrother.pickandtest.member.domain.core.Member;

public record RegisterMemberCommand(
        String nickname,
        String profileImageUrl
) {

    public Member toDomain() {
        return new Member(nickname, profileImageUrl);
    }
}
