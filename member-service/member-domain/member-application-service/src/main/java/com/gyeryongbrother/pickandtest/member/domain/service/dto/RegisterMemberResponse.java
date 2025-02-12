package com.gyeryongbrother.pickandtest.member.domain.service.dto;

import com.gyeryongbrother.pickandtest.member.domain.core.Member;

public record RegisterMemberResponse(
        String accessToken,
        String refreshToken
) {

}
