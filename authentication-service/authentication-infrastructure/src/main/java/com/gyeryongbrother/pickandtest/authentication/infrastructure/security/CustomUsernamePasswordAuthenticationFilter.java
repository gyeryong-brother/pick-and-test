package com.gyeryongbrother.pickandtest.authentication.infrastructure.security;

import static com.gyeryongbrother.pickandtest.authentication.infrastructure.exception.AuthenticationInfrastructureExceptionType.READ_BODY_FAILED;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.exception.AuthenticationInfrastructureException;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.security.dto.LoginRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
public class CustomUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final ObjectMapper objectMapper;

    public CustomUsernamePasswordAuthenticationFilter(
            ObjectMapper objectMapper,
            AuthenticationManager authenticationManager,
            AuthenticationSuccessHandler authenticationSuccessHandler,
            AuthenticationFailureHandler authenticationFailureHandler
    ) {
        this.objectMapper = objectMapper;
        setFilterProcessesUrl("/login");
        setAuthenticationManager(authenticationManager);
        setAuthenticationSuccessHandler(authenticationSuccessHandler);
        setAuthenticationFailureHandler(authenticationFailureHandler);
    }

    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws AuthenticationException {
        try {
            LoginRequest loginRequest = objectMapper.readValue(request.getInputStream(), LoginRequest.class);
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    loginRequest.username(),
                    loginRequest.password()
            );
            return getAuthenticationManager().authenticate(authentication);
        } catch (IOException e) {
            throw new AuthenticationInfrastructureException(READ_BODY_FAILED);
        }
    }
}
