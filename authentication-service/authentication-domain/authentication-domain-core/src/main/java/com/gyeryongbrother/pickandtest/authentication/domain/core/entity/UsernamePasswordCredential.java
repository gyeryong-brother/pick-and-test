package com.gyeryongbrother.pickandtest.authentication.domain.core.entity;

import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.MemberRole;

public class UsernamePasswordCredential {

    private final Long id;
    private final Long memberId;
    private final MemberRole memberRole;
    private final String username;
    private final String password;

    public UsernamePasswordCredential(
            Long id,
            Long memberId,
            MemberRole memberRole,
            String username,
            String password
    ) {
        this.id = id;
        this.memberId = memberId;
        this.memberRole = memberRole;
        this.username = username;
        this.password = password;
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

    public String username() {
        return username;
    }

    public String password() {
        return password;
    }
}
