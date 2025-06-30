package com.gyeryongbrother.pickandtest.common.authorizationresolver;

import java.util.List;

public class MemberAuthority {

    private final Long memberId;
    private final List<MemberRole> memberRoles;

    public MemberAuthority(Long memberId, List<MemberRole> memberRoles) {
        this.memberId = memberId;
        this.memberRoles = memberRoles;
    }

    public Long memberId() {
        return memberId;
    }

    public List<MemberRole> memberRoles() {
        return memberRoles;
    }
}
