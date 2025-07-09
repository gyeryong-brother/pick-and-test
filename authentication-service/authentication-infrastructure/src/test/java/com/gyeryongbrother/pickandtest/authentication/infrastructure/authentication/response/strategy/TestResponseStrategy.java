package com.gyeryongbrother.pickandtest.authentication.infrastructure.authentication.response.strategy;

import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.LoginType;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.authentication.response.HttpServletResponseFacade;

public class TestResponseStrategy extends ResponseStrategy {

    public TestResponseStrategy(
    ) {
        super(null, null);
    }

    @Override
    public LoginType support() {
        return LoginType.TEST_SUPPORTED;
    }

    @Override
    protected void responseAccessToken(HttpServletResponseFacade responseFacade, String accessToken) {

    }
}
