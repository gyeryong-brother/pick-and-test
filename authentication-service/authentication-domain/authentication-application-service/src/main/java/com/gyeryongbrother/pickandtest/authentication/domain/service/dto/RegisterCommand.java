package com.gyeryongbrother.pickandtest.authentication.domain.service.dto;

import com.gyeryongbrother.pickandtest.authentication.domain.core.entity.UsernamePasswordCredential;
import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.Member;

public record RegisterCommand(
        String username,
        String password,
        String nickname,
        String profileImageUrl
) {

    public UsernamePasswordCredential toCredential(Long memberId) {
        return new UsernamePasswordCredential(null, memberId, username, password);
    }

    public Member toDomain() {
        return new Member(null, nickname, profileImageUrl);
    }
}
