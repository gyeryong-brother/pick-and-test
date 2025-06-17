package com.gyeryongbrother.pickandtest.authentication.domain.service.ports.input;

import com.gyeryongbrother.pickandtest.authentication.domain.service.dto.LoginCommand;
import com.gyeryongbrother.pickandtest.authentication.domain.service.dto.LoginResponse;
import com.gyeryongbrother.pickandtest.authentication.domain.service.dto.RegisterCommand;
import com.gyeryongbrother.pickandtest.authentication.domain.service.dto.RegisterResponse;

public interface AuthenticationService {

    RegisterResponse register(RegisterCommand command);

    LoginResponse login(LoginCommand command);

    void logout(String refreshToken);
}
