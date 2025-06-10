package com.gyeryongbrother.pickandtest.authentication.application.dto;

public record RegisterRequest(
        String username,
        String password,
        String nickname,
        String profileImageUrl
) {
}
