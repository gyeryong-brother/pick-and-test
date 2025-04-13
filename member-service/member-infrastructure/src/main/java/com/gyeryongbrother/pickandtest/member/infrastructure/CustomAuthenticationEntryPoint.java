package com.gyeryongbrother.pickandtest.member.infrastructure;

import com.gyeryongbrother.pickandtest.member.infrastructure.exception.MemberInfrastructureException;
import com.gyeryongbrother.pickandtest.member.infrastructure.exception.MemberInfrastructureExceptionType;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        throw new MemberInfrastructureException(MemberInfrastructureExceptionType.INVALID_TOKEN_ERROR);
    }
}

