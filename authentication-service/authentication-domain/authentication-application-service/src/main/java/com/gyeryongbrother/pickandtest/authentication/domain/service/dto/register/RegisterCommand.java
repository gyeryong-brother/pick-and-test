package com.gyeryongbrother.pickandtest.authentication.domain.service.dto.register;

import com.gyeryongbrother.pickandtest.authentication.domain.core.entity.UsernamePasswordCredential;
import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.Member;
import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.MemberRole;

public record RegisterCommand(
        String username,
        String password,
        String nickname,
        String profileImageUrl
) {

    public UsernamePasswordCredential toCredential(Long memberId) {
        return new UsernamePasswordCredential(null, memberId, MemberRole.USER, username, password);
    }

    public Member toDomain() {
        return new Member(null, nickname, profileImageUrl);
    }
}
