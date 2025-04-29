package com.gyeryongbrother.pickandtest.authentication.domain.core.model;

import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.AuthenticationMethod;

public interface AuthenticationAttempt {

    AuthenticationMethod method();

    String principal();

    String secret();
}
