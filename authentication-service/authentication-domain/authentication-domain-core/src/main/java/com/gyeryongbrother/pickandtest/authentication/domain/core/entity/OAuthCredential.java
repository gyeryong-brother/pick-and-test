package com.gyeryongbrother.pickandtest.authentication.domain.core.entity;

import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.MemberRole;
import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.OAuthId;

public class OAuthCredential {

    private final Long id;
    private final Long memberId;
    private final MemberRole memberRole;
    private final OAuthId oauthId;

    public OAuthCredential(Long id, Long memberId, MemberRole memberRole, OAuthId oauthId) {
        this.id = id;
        this.memberId = memberId;
        this.memberRole = memberRole;
        this.oauthId = oauthId;
    }

    public OAuthCredential(Long memberId, MemberRole memberRole, OAuthId oauthId) {
        this(null, memberId, memberRole, oauthId);
    }

    public Long id() {
        return id;
    }

    public Long memberId() {
        return memberId;
    }

    public MemberRole memberRole() {
        return memberRole;
    }

    public OAuthId oauthId() {
        return oauthId;
    }
}
