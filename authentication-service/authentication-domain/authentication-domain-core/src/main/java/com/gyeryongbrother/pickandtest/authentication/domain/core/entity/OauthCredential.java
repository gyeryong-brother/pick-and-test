package com.gyeryongbrother.pickandtest.authentication.domain.core.entity;

import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.MemberRole;
import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.OauthId;

public class OauthCredential {

    private final Long id;
    private final Long memberId;
    private final MemberRole memberRole;
    private final OauthId oauthId;

    public OauthCredential(Long id, Long memberId, MemberRole memberRole, OauthId oauthId) {
        this.id = id;
        this.memberId = memberId;
        this.memberRole = memberRole;
        this.oauthId = oauthId;
    }

    public OauthCredential(Long memberId, MemberRole memberRole, OauthId oauthId) {
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

    public OauthId oauthId() {
        return oauthId;
    }
}
