package com.gyeryongbrother.pickandtest.authentication.domain.service.dto.login;

import com.gyeryongbrother.pickandtest.authentication.domain.core.model.AuthenticationAttempt;

public interface LoginCommand {

    AuthenticationAttempt toAuthenticationAttempt();
}
