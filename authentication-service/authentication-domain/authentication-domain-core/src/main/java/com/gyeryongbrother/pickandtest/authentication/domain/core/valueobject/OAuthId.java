package com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject;

import java.util.Objects;

public class OAuthId {

    private final OAuthType oAuthType;
    private final String value;

    public OAuthId(OAuthType oAuthType, String value) {
        this.oAuthType = oAuthType;
        this.value = value;
    }

    public OAuthType oAuthType() {
        return oAuthType;
    }

    public String value() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        OAuthId oAuthId = (OAuthId) o;
        return oAuthType == oAuthId.oAuthType && Objects.equals(value, oAuthId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(oAuthType, value);
    }
}
