package com.gyeryongbrother.pickandtest.authentication.domain.service.dto.login;

import com.gyeryongbrother.pickandtest.authentication.domain.core.model.AuthenticationAttempt;
import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.AuthenticationMethod;
import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.AuthorizationCodeAttempt;

public record OauthLoginCommand(
        AuthenticationMethod authenticationMethod,
        String code
) implements LoginCommand {

    @Override
    public AuthenticationAttempt toAuthenticationAttempt() {
        return new AuthorizationCodeAttempt(authenticationMethod, code);
    }
}
