package com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject;

public class Tokens {

    private final Long memberId;
    private final String accessToken;
    private final String refreshToken;

    public Tokens(Long memberId, String accessToken, String refreshToken) {
        this.memberId = memberId;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public Long memberId() {
        return memberId;
    }

    public String accessToken() {
        return accessToken;
    }

    public String refreshToken() {
        return refreshToken;
    }
}
