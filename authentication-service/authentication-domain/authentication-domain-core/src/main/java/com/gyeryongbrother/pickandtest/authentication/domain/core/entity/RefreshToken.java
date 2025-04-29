package com.gyeryongbrother.pickandtest.authentication.domain.core.entity;

public class RefreshToken {

    private final Long id;
    private final Long memberId;
    private final String token;

    public RefreshToken(Long id, Long memberId, String token) {
        this.id = id;
        this.memberId = memberId;
        this.token = token;
    }

    public RefreshToken(Long memberId, String token) {
        this(null, memberId, token);
    }

    public Long id() {
        return id;
    }

    public Long memberId() {
        return memberId;
    }

    public String token() {
        return token;
    }
}
