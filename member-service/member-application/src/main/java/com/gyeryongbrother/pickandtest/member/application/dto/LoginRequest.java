package com.gyeryongbrother.pickandtest.member.application.dto;

import com.gyeryongbrother.pickandtest.member.domain.service.dto.LoginCommand;

public record LoginRequest(
        String username,
        String password
) {

    public LoginCommand toCommand() {
        return new LoginCommand(username, password);
    }
}
