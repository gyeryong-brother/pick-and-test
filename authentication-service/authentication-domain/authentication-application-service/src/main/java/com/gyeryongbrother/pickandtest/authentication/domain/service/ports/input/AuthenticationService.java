package com.gyeryongbrother.pickandtest.authentication.domain.service.ports.input;

import com.gyeryongbrother.pickandtest.authentication.domain.service.dto.login.LoginResponse;
import com.gyeryongbrother.pickandtest.authentication.domain.service.dto.login.UsernamePasswordLoginCommand;
import com.gyeryongbrother.pickandtest.authentication.domain.service.dto.register.RegisterCommand;
import com.gyeryongbrother.pickandtest.authentication.domain.service.dto.register.RegisterResponse;

public interface AuthenticationService {

    RegisterResponse register(RegisterCommand command);

    LoginResponse login(UsernamePasswordLoginCommand command);

    void logout(String refreshToken);
}
