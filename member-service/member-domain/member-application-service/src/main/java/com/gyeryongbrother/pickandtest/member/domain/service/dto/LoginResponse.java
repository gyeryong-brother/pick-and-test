package com.gyeryongbrother.pickandtest.member.domain.service.dto;

public record LoginResponse(
        String accessToken,
        String refreshToken
) {

}
