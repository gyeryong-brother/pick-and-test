package com.gyeryongbrother.pickandtest.authentication.domain.service.ports.input;

import com.gyeryongbrother.pickandtest.authentication.domain.service.dto.register.RegisterCommand;
import com.gyeryongbrother.pickandtest.authentication.domain.service.dto.register.RegisterResponse;

public interface AuthenticationService {

    RegisterResponse register(RegisterCommand command);

    void logout(String refreshToken);
}
