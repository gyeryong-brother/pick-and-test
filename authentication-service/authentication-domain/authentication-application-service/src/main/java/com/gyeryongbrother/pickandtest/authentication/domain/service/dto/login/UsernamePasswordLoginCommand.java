package com.gyeryongbrother.pickandtest.authentication.domain.service.dto.login;

public record UsernamePasswordLoginCommand(
        String username,
        String password
) {
}
