package com.gyeryongbrother.pickandtest.authentication.infrastructure.security.dto;

public record LoginRequest(
        String username,
        String password
) {
}
