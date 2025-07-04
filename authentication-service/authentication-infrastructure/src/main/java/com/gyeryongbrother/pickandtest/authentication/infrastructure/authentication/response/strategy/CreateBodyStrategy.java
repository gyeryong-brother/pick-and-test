package com.gyeryongbrother.pickandtest.authentication.infrastructure.authentication.response.strategy;

import static com.gyeryongbrother.pickandtest.authentication.infrastructure.exception.AuthenticationInfrastructureExceptionType.CREATE_BODY_FAILED;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gyeryongbrother.pickandtest.authentication.domain.service.ports.output.RefreshTokenRepository;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.exception.AuthenticationInfrastructureException;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.authentication.jwt.JwtProvider;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.authentication.response.HttpServletResponseFacade;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.authentication.LoginType;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.authentication.response.strategy.dto.LoginResponse;
import org.springframework.stereotype.Component;

@Component
class CreateBodyStrategy extends ResponseStrategy {

    private final ObjectMapper objectMapper;

    public CreateBodyStrategy(
            JwtProvider jwtProvider,
            RefreshTokenRepository refreshTokenRepository,
            ObjectMapper objectMapper
    ) {
        super(jwtProvider, refreshTokenRepository);
        this.objectMapper = objectMapper;
    }

    @Override
    public LoginType support() {
        return LoginType.USERNAME_PASSWORD;
    }

    @Override
    protected void responseAccessToken(HttpServletResponseFacade responseFacade, String accessToken) {
        responseFacade.addBody(accessTokenBody(accessToken));
    }

    private String accessTokenBody(String accessToken) {
        LoginResponse loginResponse = new LoginResponse(accessToken);
        try {
            return objectMapper.writeValueAsString(loginResponse);
        } catch (JsonProcessingException e) {
            throw new AuthenticationInfrastructureException(CREATE_BODY_FAILED);
        }
    }
}
