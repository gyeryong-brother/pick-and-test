package com.gyeryongbrother.pickandtest.authentication.infrastructure.adapter;

import static com.gyeryongbrother.pickandtest.authentication.domain.service.exception.AuthenticationServiceExceptionType.AUTHENTICATION_METHOD_DUPLICATED;
import static com.gyeryongbrother.pickandtest.authentication.infrastructure.exception.AuthenticationInfrastructureExceptionType.OAUTH_SERVER_NOT_SUPPORTED;
import static java.util.Map.entry;
import static java.util.stream.Collectors.toMap;

import com.gyeryongbrother.pickandtest.authentication.domain.core.model.AuthenticationAttempt;
import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.AuthenticationMethod;
import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.Tokens;
import com.gyeryongbrother.pickandtest.authentication.domain.service.exception.AuthenticationServiceException;
import com.gyeryongbrother.pickandtest.authentication.domain.service.ports.output.Authenticator;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.exception.AuthenticationInfrastructureException;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.strategy.AuthenticationStrategy;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.stream.Stream;
import org.springframework.stereotype.Component;

@Component
public class AuthenticatorImpl implements Authenticator {

    private final Map<AuthenticationMethod, AuthenticationStrategy> strategiesByMethod;

    public AuthenticatorImpl(Set<AuthenticationStrategy> strategies) {
        strategiesByMethod = strategies.stream()
                .flatMap(this::methodStrategyEntries)
                .collect(toMap(Entry::getKey, Entry::getValue, mergeFunction()));
    }

    private Stream<Entry<AuthenticationMethod, AuthenticationStrategy>> methodStrategyEntries(
            AuthenticationStrategy strategy
    ) {
        return strategy.support().stream()
                .map(method -> entry(method, strategy));
    }

    private BinaryOperator<AuthenticationStrategy> mergeFunction() {
        return (existing, replacement) -> {
            throw new AuthenticationServiceException(AUTHENTICATION_METHOD_DUPLICATED);
        };
    }

    @Override
    public Tokens authenticate(AuthenticationAttempt attempt) {
        return Optional.ofNullable(strategiesByMethod.get(attempt.method()))
                .orElseThrow(() -> new AuthenticationInfrastructureException(OAUTH_SERVER_NOT_SUPPORTED))
                .authenticate(attempt);
    }
}
