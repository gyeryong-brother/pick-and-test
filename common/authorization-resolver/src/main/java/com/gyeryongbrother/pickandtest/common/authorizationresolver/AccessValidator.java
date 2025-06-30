package com.gyeryongbrother.pickandtest.common.authorizationresolver;

import org.springframework.core.MethodParameter;

public interface AccessValidator {

    boolean support(MethodParameter parameter);

    void validate(MemberAuthority memberAuthority);
}
