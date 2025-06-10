package com.gyeryongbrother.pickandtest.authentication.domain.service.ports.input;

import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.AuthenticationMethod;
import com.gyeryongbrother.pickandtest.authentication.domain.service.dto.LoginPageResponse;

public interface AuthenticationQueryService {

    LoginPageResponse getLoginPage(AuthenticationMethod authenticationMethod);
}
