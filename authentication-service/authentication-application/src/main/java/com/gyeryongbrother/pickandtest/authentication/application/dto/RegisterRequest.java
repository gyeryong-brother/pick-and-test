package com.gyeryongbrother.pickandtest.authentication.application.dto;

import com.gyeryongbrother.pickandtest.authentication.domain.service.dto.register.RegisterCommand;

public record RegisterRequest(
        String username,
        String password,
        String nickname,
        String profileImageUrl
) {

    public RegisterCommand toCommand() {
        return new RegisterCommand(username, password, nickname, profileImageUrl);
    }
}
