package com.gyeryongbrother.pickandtest.authentication.domain.service.dto.login;

import com.gyeryongbrother.pickandtest.authentication.domain.core.model.AuthenticationAttempt;
import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.UsernamePasswordAttempt;

public record UsernamePasswordLoginCommand(
        String username,
        String password
) implements LoginCommand {

    @Override
    public AuthenticationAttempt toAuthenticationAttempt() {
        return new UsernamePasswordAttempt(username, password);
    }
}
