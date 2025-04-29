package com.gyeryongbrother.pickandtest.authentication.domain.service.exception;

import static com.gyeryongbrother.pickandtest.authentication.domain.core.exception.AuthenticationCoreExceptionType.AUTHENTICATION_METHOD_NOT_MATCHED;
import static com.gyeryongbrother.pickandtest.authentication.domain.core.exception.AuthenticationCoreExceptionType.INVALID_USERROLE;
import static com.gyeryongbrother.pickandtest.authentication.domain.service.exception.AuthenticationServiceExceptionType.CORE_EXCEPTION_NOT_REGISTERED;
import static com.gyeryongbrother.pickandtest.authentication.domain.service.exception.AuthenticationServiceExceptionType.CORE_EXCEPTION_SHOULD_BE_REGISTERED;
import static com.gyeryongbrother.pickandtest.authentication.domain.service.exception.AuthenticationServiceExceptionType.SERVICE_EXCEPTION_AUTHENTICATION_METHOD_NOT_MATCHED;
import static com.gyeryongbrother.pickandtest.authentication.domain.service.exception.AuthenticationServiceExceptionType.USERROLE_NONEXISTS;

import com.gyeryongbrother.pickandtest.authentication.domain.core.exception.AuthenticationCoreExceptionType;
import com.gyeryongbrother.pickandtest.authentication.domain.core.exception.CoreExceptionType;
import java.util.Arrays;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class CoreExceptionMapper {

    private final Map<CoreExceptionType, BaseExceptionType> values = Map.of(
            INVALID_USERROLE, USERROLE_NONEXISTS,
            AUTHENTICATION_METHOD_NOT_MATCHED, SERVICE_EXCEPTION_AUTHENTICATION_METHOD_NOT_MATCHED
    );

    public CoreExceptionMapper() {
        validate();
    }

    private void validate() {
        Arrays.stream(AuthenticationCoreExceptionType.values())
                .forEach(this::validate);
    }

    private void validate(AuthenticationCoreExceptionType exceptionType) {
        if (values.containsKey(exceptionType)) {
            return;
        }
        throw new AuthenticationServiceException(CORE_EXCEPTION_SHOULD_BE_REGISTERED);
    }

    public BaseExceptionType map(CoreExceptionType exceptionType) {
        if (values.containsKey(exceptionType)) {
            return values.get(exceptionType);
        }
        throw new AuthenticationServiceException(CORE_EXCEPTION_NOT_REGISTERED);
    }
}
