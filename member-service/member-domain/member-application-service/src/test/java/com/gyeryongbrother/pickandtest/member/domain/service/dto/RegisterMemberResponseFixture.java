package com.gyeryongbrother.pickandtest.member.domain.service.dto;

import com.gyeryongbrother.pickandtest.member.domain.core.MemberRole;

public class RegisterMemberResponseFixture {

    public static RegisterMemberResponse registerMemberResponse() {
        return new RegisterMemberResponse(
                1L,
                MemberRole.ROLE_USER,
                "nickname",
                "profileImageUrl"
        );
    }
}
