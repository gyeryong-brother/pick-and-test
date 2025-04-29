package com.gyeryongbrother.pickandtest.authentication.domain.service.ports.input;

import com.gyeryongbrother.pickandtest.authentication.domain.service.dto.LoginCommand;
import com.gyeryongbrother.pickandtest.authentication.domain.service.dto.LoginResponse;

public interface AuthenticationService {

    LoginResponse login(LoginCommand loginCommand);

    void logout(String refreshToken);
}
