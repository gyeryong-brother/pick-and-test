package com.gyeryongbrother.pickandtest.authentication.domain.service.dto;

public record LoginResponse(
        String accessToken,
        String refreshToken
) {
}
