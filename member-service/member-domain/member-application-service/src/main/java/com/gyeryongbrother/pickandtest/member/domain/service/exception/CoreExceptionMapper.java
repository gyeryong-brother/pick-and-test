package com.gyeryongbrother.pickandtest.member.domain.service.exception;

import static com.gyeryongbrother.pickandtest.member.domain.core.exception.MemberCoreExceptionType.INVALID_USERROLE;
import static com.gyeryongbrother.pickandtest.member.domain.service.exception.MemberServiceExceptionType.CORE_EXCEPTION_NOT_REGISTERED;
import static com.gyeryongbrother.pickandtest.member.domain.service.exception.MemberServiceExceptionType.CORE_EXCEPTION_SHOULD_BE_REGISTERED;
import static com.gyeryongbrother.pickandtest.member.domain.service.exception.MemberServiceExceptionType.USERROLE_NONEXISTS;

import com.gyeryongbrother.pickandtest.member.domain.core.exception.CoreExceptionType;
import com.gyeryongbrother.pickandtest.member.domain.core.exception.MemberCoreExceptionType;
import java.util.Arrays;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class CoreExceptionMapper {

    private final Map<CoreExceptionType, BaseExceptionType> values = Map.of(
            INVALID_USERROLE, USERROLE_NONEXISTS
    );

    public CoreExceptionMapper() {
        validate();
    }

    private void validate() {
        Arrays.stream(MemberCoreExceptionType.values())
                .forEach(this::validate);
    }

    private void validate(MemberCoreExceptionType exceptionType) {
        if (values.containsKey(exceptionType)) {
            return;
        }
        throw new MemberServiceException(CORE_EXCEPTION_SHOULD_BE_REGISTERED);
    }

    public BaseExceptionType map(CoreExceptionType exceptionType) {
        if (values.containsKey(exceptionType)) {
            return values.get(exceptionType);
        }
        throw new MemberServiceException(CORE_EXCEPTION_NOT_REGISTERED);
    }
}
