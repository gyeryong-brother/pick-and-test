package com.gyeryongbrother.pickandtest.member.domain.fixture.entity;

import com.gyeryongbrother.pickandtest.member.domain.core.Member;
import com.gyeryongbrother.pickandtest.member.domain.core.MemberRole;

public class MemberFixture {

    public static Member member() {
        return new Member(1L, MemberRole.ROLE_USER, "nickname", "profileImageUrl");
    }
}
