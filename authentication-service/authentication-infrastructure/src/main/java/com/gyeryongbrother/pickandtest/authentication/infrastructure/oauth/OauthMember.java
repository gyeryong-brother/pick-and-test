package com.gyeryongbrother.pickandtest.authentication.infrastructure.oauth;

import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.Member;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OauthMember {

    private final OauthId oauthId;
    private final String nickname;
    private final String profileImageUrl;

    public Member toDomain() {
        return new Member(null, nickname, profileImageUrl);
    }

    public OauthId oauthId() {
        return oauthId;
    }
}
