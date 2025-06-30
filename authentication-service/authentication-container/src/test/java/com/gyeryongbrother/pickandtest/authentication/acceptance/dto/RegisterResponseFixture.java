package com.gyeryongbrother.pickandtest.authentication.acceptance.dto;

import com.gyeryongbrother.pickandtest.authentication.domain.service.dto.register.RegisterResponse;

public class RegisterResponseFixture {

    public static RegisterResponse registerResponse() {
        return new RegisterResponse(1L, "username");
    }
}
