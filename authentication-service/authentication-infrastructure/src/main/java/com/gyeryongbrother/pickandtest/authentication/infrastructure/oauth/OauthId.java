package com.gyeryongbrother.pickandtest.authentication.infrastructure.oauth;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OauthId {

    private final String oauthServerId;
    private final OauthServerType oauthServerType;
}
