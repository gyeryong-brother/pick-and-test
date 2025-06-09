package com.gyeryongbrother.pickandtest.authentication.infrastructure.oauth;

public interface AuthCodeRequestUrlProvider {

    OauthServerType supportServer();

    String provide();
}
