package com.gyeryongbrother.pickandtest.member.domain.service.dto;

public record LoginCommand(
        String userId,
        String password
) {
}
