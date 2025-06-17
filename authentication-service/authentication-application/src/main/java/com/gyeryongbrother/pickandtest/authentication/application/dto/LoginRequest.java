package com.gyeryongbrother.pickandtest.authentication.application.dto;

import com.gyeryongbrother.pickandtest.authentication.domain.service.dto.UsernamePasswordLoginCommand;

public record LoginRequest(
        String username,
        String password
) {

    public UsernamePasswordLoginCommand toCommand() {
        return new UsernamePasswordLoginCommand(username, password);
    }
}
