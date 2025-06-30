package com.gyeryongbrother.pickandtest.common.authorizationresolver;

import org.springframework.core.MethodParameter;

public class GuestAccessValidator implements AccessValidator {

    @Override
    public boolean support(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(Guest.class);
    }

    @Override
    public void validate(MemberAuthority memberAuthority) {
    }
}
