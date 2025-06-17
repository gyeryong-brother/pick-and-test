package com.gyeryongbrother.pickandtest.authentication.application.dto;

import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.AuthenticationMethod;
import com.gyeryongbrother.pickandtest.authentication.domain.service.dto.login.OauthLoginCommand;

public record OauthLoginRequest(
        String code
) {

    public OauthLoginCommand toCommand(AuthenticationMethod authenticationMethod) {
        return new OauthLoginCommand(authenticationMethod, code);
    }
}
