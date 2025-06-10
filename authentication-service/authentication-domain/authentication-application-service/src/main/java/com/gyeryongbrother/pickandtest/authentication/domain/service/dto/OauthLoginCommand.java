package com.gyeryongbrother.pickandtest.authentication.domain.service.dto;

import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.AuthenticationMethod;

public record OauthLoginCommand(
        AuthenticationMethod authenticationMethod,
        String code
) {
}
