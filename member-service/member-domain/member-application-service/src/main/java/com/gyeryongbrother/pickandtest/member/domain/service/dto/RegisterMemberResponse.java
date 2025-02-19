package com.gyeryongbrother.pickandtest.member.domain.service.dto;

public record RegisterMemberResponse(
        String accessToken,
        String refreshToken
) {

}
