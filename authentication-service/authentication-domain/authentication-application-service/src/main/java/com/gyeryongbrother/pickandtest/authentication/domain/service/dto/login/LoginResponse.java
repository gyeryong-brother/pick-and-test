package com.gyeryongbrother.pickandtest.authentication.domain.service.dto.login;

public record LoginResponse(
        String accessToken,
        String refreshToken
) {
}
