package com.gyeryongbrother.pickandtest.authentication.infrastructure.authentication.response.strategy;

import static com.gyeryongbrother.pickandtest.authentication.infrastructure.exception.AuthenticationInfrastructureExceptionType.LOGIN_TYPE_NOT_SUPPORTED;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.LoginType;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.exception.AuthenticationInfrastructureException;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import org.springframework.stereotype.Component;

@Component
public class ResponseStrategyProvider {

    private final Map<LoginType, ResponseStrategy> strategiesByType;

    public ResponseStrategyProvider(Set<ResponseStrategy> strategies) {
        strategiesByType = strategies.stream()
                .collect(toMap(ResponseStrategy::support, identity()));
    }

    public ResponseStrategy getStrategyByType(LoginType loginType) {
        return Optional.ofNullable(strategiesByType.get(loginType))
                .orElseThrow(() -> new AuthenticationInfrastructureException(LOGIN_TYPE_NOT_SUPPORTED));
    }
}
