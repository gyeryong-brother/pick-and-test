package com.gyeryongbrother.pickandtest.authentication.infrastructure.security;

import static com.gyeryongbrother.pickandtest.authentication.infrastructure.exception.AuthenticationInfrastructureExceptionType.CREATE_BODY_FAILED;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gyeryongbrother.pickandtest.authentication.domain.service.exception.BaseException;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.exception.AuthenticationInfrastructureException;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.security.dto.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    private final ObjectMapper objectMapper;

    @Override
    public void onAuthenticationFailure(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException exception
    ) {
        HttpServletResponseFacade responseFacade = new HttpServletResponseFacade(response);
        responseFacade.addBody(createErrorMessageBody(errorMessage(exception)));
    }

    private String errorMessage(AuthenticationException exception) {
        Throwable cause = exception.getCause();
        if (cause instanceof BaseException baseException) {
            return baseException.exceptionType().errorMessage();
        }
        return exception.getMessage();
    }

    private String createErrorMessageBody(String message) {
        try {
            return objectMapper.writeValueAsString(new ErrorResponse(message));
        } catch (JsonProcessingException e) {
            throw new AuthenticationInfrastructureException(CREATE_BODY_FAILED);
        }
    }
}
