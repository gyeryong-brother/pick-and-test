package com.gyeryongbrother.pickandtest.authentication.acceptance.dto;

import com.gyeryongbrother.pickandtest.authentication.infrastructure.authentication.usernamepassword.dto.LoginRequest;

public class LoginRequestFixture {

    public static LoginRequest loginRequest() {
        return new LoginRequest("username", "password");
    }
}
