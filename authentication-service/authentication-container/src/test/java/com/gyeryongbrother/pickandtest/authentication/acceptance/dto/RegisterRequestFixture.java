package com.gyeryongbrother.pickandtest.authentication.acceptance.dto;

import com.gyeryongbrother.pickandtest.authentication.application.dto.RegisterRequest;

public class RegisterRequestFixture {

    public static RegisterRequest registerRequest() {
        return new RegisterRequest("username", "password", "odo27", "profileImageUrl");
    }
}
