package com.gyeryongbrother.pickandtest.authentication.infrastructure.authentication.response;

import static com.gyeryongbrother.pickandtest.authentication.infrastructure.exception.AuthenticationInfrastructureExceptionType.CREATE_BODY_FAILED;
import static com.gyeryongbrother.pickandtest.authentication.infrastructure.exception.AuthenticationInfrastructureExceptionType.REDIRECT_FAILED;

import com.gyeryongbrother.pickandtest.authentication.infrastructure.exception.AuthenticationInfrastructureException;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HttpServletResponseFacade {

    private static final int ONE_WEEK = 7 * 24 * 60 * 60;

    private final HttpServletResponse response;

    public HttpServletResponseFacade(
            HttpServletResponse response
    ) {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        this.response = response;
    }

    public void redirect(String url) {
        try {
            response.sendRedirect(url);
        } catch (IOException e) {
            throw new AuthenticationInfrastructureException(REDIRECT_FAILED);
        }
    }

    public void addBody(String body) {
        try {
            response.getWriter().write(body);
        } catch (IOException e) {
            throw new AuthenticationInfrastructureException(CREATE_BODY_FAILED);
        }
    }

    public void addRefreshTokenCookie(String refreshToken) {
        String cookie = createRefreshTokenCookie(refreshToken);
        response.addHeader("Set-Cookie", cookie);
    }

    private String createRefreshTokenCookie(String refreshToken) {
        return String.format(
                "refresh-token=%s; Path=/; Domain=.pickandtest.com; Max-Age=%d; HttpOnly; Secure; SameSite=None",
                refreshToken, ONE_WEEK
        );
    }
}
