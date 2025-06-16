package com.gyeryongbrother.pickandtest.authentication.infrastructure.config;

import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.AuthenticationMethod;
import org.springframework.core.convert.converter.Converter;

public class AuthenticationMethodConverter implements Converter<String, AuthenticationMethod> {

    @Override
    public AuthenticationMethod convert(String source) {
        return AuthenticationMethod.from(source);
    }
}
