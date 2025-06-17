package com.gyeryongbrother.pickandtest.authentication.infrastructure.strategy;

import static com.gyeryongbrother.pickandtest.authentication.domain.service.exception.AuthenticationServiceExceptionType.AUTHENTICATION_METHOD_DUPLICATED;

import com.gyeryongbrother.pickandtest.authentication.domain.core.model.AuthenticationAttempt;
import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.AuthenticationMethod;
import com.gyeryongbrother.pickandtest.authentication.domain.service.exception.AuthenticationServiceException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationStrategyFactoryImpl implements AuthenticationStrategyFactory {

    private final Map<AuthenticationMethod, AuthenticationStrategy> strategiesByMethod;

    public AuthenticationStrategyFactoryImpl(List<AuthenticationStrategy> strategies) {
        strategiesByMethod = strategies.stream()
                .flatMap(this::methodStrategyEntries)
                .collect(Collectors.toMap(Entry::getKey, Entry::getValue, mergeFunction()));
    }

    private Stream<Entry<AuthenticationMethod, AuthenticationStrategy>> methodStrategyEntries(
            AuthenticationStrategy strategy
    ) {
        return strategy.support().stream()
                .map(method -> Map.entry(method, strategy));
    }

    private BinaryOperator<AuthenticationStrategy> mergeFunction() {
        return (existing, replacement) -> {
            throw new AuthenticationServiceException(AUTHENTICATION_METHOD_DUPLICATED);
        };
    }

    @Override
    public AuthenticationStrategy resolve(AuthenticationAttempt authenticationAttempt) {
        AuthenticationMethod method = authenticationAttempt.method();
        return strategiesByMethod.get(method);
    }
}
