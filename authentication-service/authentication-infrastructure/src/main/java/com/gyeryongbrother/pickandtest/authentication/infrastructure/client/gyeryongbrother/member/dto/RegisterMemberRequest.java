package com.gyeryongbrother.pickandtest.authentication.infrastructure.client.gyeryongbrother.member.dto;

public record RegisterMemberRequest(
        String nickname,
        String profileImageUrl
) {
}
