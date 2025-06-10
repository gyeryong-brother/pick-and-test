package com.gyeryongbrother.pickandtest.authentication.domain.service.ports.input;

import com.gyeryongbrother.pickandtest.authentication.domain.service.dto.LoginCommand;
import com.gyeryongbrother.pickandtest.authentication.domain.service.dto.LoginResponse;
import com.gyeryongbrother.pickandtest.authentication.domain.service.dto.OauthLoginCommand;
import com.gyeryongbrother.pickandtest.authentication.domain.service.dto.RegisterCommand;

public interface AuthenticationService {

    LoginResponse register(RegisterCommand command);

    LoginResponse login(LoginCommand loginCommand);

    LoginResponse oauthLogin(OauthLoginCommand command);

    void logout(String refreshToken);
}
