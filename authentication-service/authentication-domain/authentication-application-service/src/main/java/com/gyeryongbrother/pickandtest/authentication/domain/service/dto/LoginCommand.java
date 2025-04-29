package com.gyeryongbrother.pickandtest.authentication.domain.service.dto;

import com.gyeryongbrother.pickandtest.authentication.domain.core.model.AuthenticationAttempt;
import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.UsernamePasswordAttempt;

public record LoginCommand(
        String username,
        String password
) {

    public AuthenticationAttempt toDomain() {
        return new UsernamePasswordAttempt(username, password);
    }
}
