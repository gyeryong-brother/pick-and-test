package com.gyeryongbrother.pickandtest.authentication.infrastructure.authentication.oauth.member;

import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.Member;
import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.OAuthId;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OAuthMember {

    private final OAuthId oauthId;
    private final String nickname;
    private final String profileImageUrl;

    public Member toDomain() {
        return new Member(nickname, profileImageUrl);
    }

    public OAuthId oauthId() {
        return oauthId;
    }
}
