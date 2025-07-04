package com.gyeryongbrother.pickandtest.authentication.infrastructure.authentication.usernamepassword.dto;

public record LoginRequest(
        String username,
        String password
) {
}
