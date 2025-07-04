package com.gyeryongbrother.pickandtest.authentication.infrastructure.response.strategy;

import static com.gyeryongbrother.pickandtest.authentication.infrastructure.exception.AuthenticationInfrastructureExceptionType.CREATE_BODY_FAILED;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.exception.AuthenticationInfrastructureException;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.jwt.JwtProvider;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.response.HttpServletResponseFacade;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.security.LoginType;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.security.dto.LoginResponse;
import org.springframework.stereotype.Component;

@Component
public class CreateBodyStrategy extends ResponseStrategy {

    private final ObjectMapper objectMapper;

    public CreateBodyStrategy(JwtProvider jwtProvider, ObjectMapper objectMapper) {
        super(jwtProvider);
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
