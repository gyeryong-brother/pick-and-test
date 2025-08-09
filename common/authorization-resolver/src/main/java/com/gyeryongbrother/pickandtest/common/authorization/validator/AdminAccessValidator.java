package com.gyeryongbrother.pickandtest.common.authorization.validator;

import com.gyeryongbrother.pickandtest.common.authorization.annotation.Admin;
import com.gyeryongbrother.pickandtest.common.authorization.domain.MemberAuthority;
import com.gyeryongbrother.pickandtest.common.authorization.domain.MemberRole;
import java.util.List;
import org.springframework.core.MethodParameter;

public class AdminAccessValidator implements AccessValidator {

    @Override
    public boolean support(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(Admin.class);
    }

    @Override
    public void validate(MemberAuthority memberAuthority) {
        List<MemberRole> memberRoles = memberAuthority.memberRoles();
        if (memberRoles.contains(MemberRole.ADMIN)) {
            return;
        }
        throw new IllegalArgumentException();
    }
}
