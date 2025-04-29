package com.gyeryongbrother.pickandtest.authentication.domain.core.model;

import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.AuthenticationMethod;

public interface RegisteredCredential {

    AuthenticationMethod method();

    Long principal();

    boolean matches(Matcher matcher);
}
