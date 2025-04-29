package com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject;

public class Tokens {

    private final String accessToken;
    private final String refreshToken;

    public Tokens(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public String accessToken() {
        return accessToken;
    }

    public String refreshToken() {
        return refreshToken;
    }
}
