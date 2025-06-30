package com.gyeryongbrother.pickandtest.common.authorization.validator;

import com.gyeryongbrother.pickandtest.common.authorization.annotation.Guest;
import com.gyeryongbrother.pickandtest.common.authorization.domain.MemberAuthority;
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
