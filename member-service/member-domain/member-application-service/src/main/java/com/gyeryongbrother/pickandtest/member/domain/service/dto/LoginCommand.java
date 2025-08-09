package com.gyeryongbrother.pickandtest.member.domain.service.dto;

public record LoginCommand(
        String username,
        String password
) {
}
