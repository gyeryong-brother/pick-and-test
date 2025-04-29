package com.gyeryongbrother.pickandtest.authentication.infrastructure.strategy;

import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.AuthenticationContext;
import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.AuthenticationMethod;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationStrategyFactoryImpl implements AuthenticationStrategyFactory {

    private final Map<AuthenticationMethod, AuthenticationStrategy> strategiesByMethod;

    public AuthenticationStrategyFactoryImpl(List<AuthenticationStrategy> strategies) {
        strategiesByMethod = strategies.stream()
                .collect(Collectors.toMap(AuthenticationStrategy::method, Function.identity()));
    }

    @Override
    public AuthenticationStrategy resolve(AuthenticationContext authenticationContext) {
        AuthenticationMethod method = authenticationContext.method();
        return strategiesByMethod.get(method);
    }
}
