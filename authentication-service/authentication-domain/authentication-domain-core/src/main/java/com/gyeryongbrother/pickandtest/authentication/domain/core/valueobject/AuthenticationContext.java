package com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject;

import static com.gyeryongbrother.pickandtest.authentication.domain.core.exception.AuthenticationCoreExceptionType.AUTHENTICATION_METHOD_NOT_MATCHED;

import com.gyeryongbrother.pickandtest.authentication.domain.core.exception.AuthenticationCoreException;
import com.gyeryongbrother.pickandtest.authentication.domain.core.model.AuthenticationAttempt;
import com.gyeryongbrother.pickandtest.authentication.domain.core.model.RegisteredCredential;

public class AuthenticationContext {

    private final AuthenticationAttempt authenticationAttempt;
    private final RegisteredCredential registeredCredential;

    public AuthenticationContext(
            AuthenticationAttempt authenticationAttempt,
            RegisteredCredential registeredCredential
    ) {
        validateMethod(authenticationAttempt, registeredCredential);
        this.authenticationAttempt = authenticationAttempt;
        this.registeredCredential = registeredCredential;
    }

    private void validateMethod(
            AuthenticationAttempt authenticationAttempt,
            RegisteredCredential registeredCredential
    ) {
        AuthenticationMethod attemptMethod = authenticationAttempt.method();
        AuthenticationMethod registeredMethod = registeredCredential.method();
        if (attemptMethod == registeredMethod) {
            return;
        }
        throw new AuthenticationCoreException(AUTHENTICATION_METHOD_NOT_MATCHED);
    }

    public AuthenticationMethod method() {
        return authenticationAttempt.method();
    }

    public AuthenticationAttempt authenticationAttempt() {
        return authenticationAttempt;
    }

    public RegisteredCredential registeredCredential() {
        return registeredCredential;
    }
}
