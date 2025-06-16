package com.gyeryongbrother.pickandtest.authentication.infrastructure.client.kakao.token;

import com.gyeryongbrother.pickandtest.authentication.infrastructure.client.common.RequestBody;

public class TokenRequestBody extends RequestBody {

    private static final String GRANT_TYPE_KEY = "grant_type";
    private static final String GRANT_TYPE_VALUE = "authorization_code";
    private static final String CLIENT_ID_KEY = "client_id";
    private static final String REDIRECT_URI_KEY = "redirect_uri";
    private static final String CODE_KEY = "code";
    private static final String CLIENT_SECRET_KEY = "client_secret";

    public TokenRequestBody(
            String clientId,
            String redirectUri,
            String authorizationCode,
            String clientSecret
    ) {
        add(GRANT_TYPE_KEY, GRANT_TYPE_VALUE);
        add(CLIENT_ID_KEY, clientId);
        add(REDIRECT_URI_KEY, redirectUri);
        add(CODE_KEY, authorizationCode);
        add(CLIENT_SECRET_KEY, clientSecret);
    }
}
