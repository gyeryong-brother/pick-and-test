package com.gyeryongbrother.pickandtest.common.authorization.validator;

import com.gyeryongbrother.pickandtest.common.authorization.domain.MemberAuthority;
import org.springframework.core.MethodParameter;

public interface AccessValidator {

    boolean support(MethodParameter parameter);

    void validate(MemberAuthority memberAuthority);
}
