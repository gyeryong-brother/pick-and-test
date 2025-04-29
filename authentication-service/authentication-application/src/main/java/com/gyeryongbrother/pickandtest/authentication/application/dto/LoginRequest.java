package com.gyeryongbrother.pickandtest.authentication.application.dto;

import com.gyeryongbrother.pickandtest.authentication.domain.service.dto.LoginCommand;

public record LoginRequest(
        String username,
        String password
) {

    public LoginCommand toCommand() {
        return new LoginCommand(username, password);
    }
}
