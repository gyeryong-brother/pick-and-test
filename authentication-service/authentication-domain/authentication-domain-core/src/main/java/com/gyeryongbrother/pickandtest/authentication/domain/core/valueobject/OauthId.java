package com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject;

import java.util.Objects;

public class OauthId {

    private final AuthenticationMethod authenticationMethod;
    private final String oauthServerId;

    public OauthId(AuthenticationMethod authenticationMethod, String oauthServerId) {
        this.authenticationMethod = authenticationMethod;
        this.oauthServerId = oauthServerId;
    }

    public AuthenticationMethod authenticationMethod() {
        return authenticationMethod;
    }

    public String oauthServerId() {
        return oauthServerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        OauthId oauthId = (OauthId) o;
        return authenticationMethod == oauthId.authenticationMethod && Objects.equals(oauthServerId,
                oauthId.oauthServerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(authenticationMethod, oauthServerId);
    }
}
