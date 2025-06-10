package com.gyeryongbrother.pickandtest.authentication.infrastructure.client.gyeryongbrother.member.dto;

import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.Member;

public record RegisterMemberResponse(
        Long id,
        String nickname,
        String profileImageUrl
) {

    public Member toDomain() {
        return new Member(id, nickname, profileImageUrl);
    }
}
