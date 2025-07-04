package com.gyeryongbrother.pickandtest.authentication.infrastructure.response;

import static com.gyeryongbrother.pickandtest.authentication.infrastructure.exception.AuthenticationInfrastructureExceptionType.CREATE_BODY_FAILED;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

import com.gyeryongbrother.pickandtest.authentication.infrastructure.exception.AuthenticationInfrastructureException;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.jwt.Tokenizable;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.response.strategy.ResponseStrategy;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.security.LoginType;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import org.springframework.stereotype.Component;

@Component
public class ResponseStrategyComposite {

    private final Map<LoginType, ResponseStrategy> strategiesByType;

    public ResponseStrategyComposite(Set<ResponseStrategy> strategies) {
        strategiesByType = strategies.stream()
                .collect(toMap(ResponseStrategy::support, identity()));
    }

    public void response(HttpServletResponseFacade responseFacade, Tokenizable tokenizable) {
        Optional.ofNullable(strategiesByType.get(tokenizable.type()))
                .orElseThrow(() -> new AuthenticationInfrastructureException(CREATE_BODY_FAILED))
                .response(responseFacade, tokenizable);
    }
}
