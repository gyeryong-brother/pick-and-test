package com.gyeryongbrother.pickandtest.common.authorizationresolver;

import java.util.List;
import org.springframework.core.MethodParameter;

public class AccessValidatorComposite {

    private final List<AccessValidator> validators;

    public AccessValidatorComposite() {
        validators = List.of(
                new GuestAccessValidator(),
                new UserAccessValidator(),
                new AdminAccessValidator()
        );
    }

    public void validateAccess(MemberAuthority memberAuthority, MethodParameter methodParameter) {
        validators.stream()
                .filter(it -> it.support(methodParameter))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("df"))
                .validate(memberAuthority);
    }
}
