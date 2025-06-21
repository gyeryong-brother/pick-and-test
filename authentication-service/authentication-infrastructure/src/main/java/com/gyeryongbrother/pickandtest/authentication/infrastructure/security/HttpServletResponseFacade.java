package com.gyeryongbrother.pickandtest.authentication.infrastructure.security;

import static com.gyeryongbrother.pickandtest.authentication.infrastructure.exception.AuthenticationInfrastructureExceptionType.CREATE_BODY_FAILED;

import com.gyeryongbrother.pickandtest.authentication.infrastructure.exception.AuthenticationInfrastructureException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HttpServletResponseFacade {

    private static final String REFRESH_TOKEN_NAME = "refresh-token";
    private static final String ROOT_PATH = "/";
    private static final int ONE_WEEK = 7 * 24 * 60 * 60;

    private final HttpServletResponse response;

    public HttpServletResponseFacade(
            HttpServletResponse response,
            String contentType
    ) {
        response.setContentType(contentType);
        response.setCharacterEncoding("UTF-8");
        this.response = response;
    }

    public void addBody(String body) {
        try {
            response.getWriter().write(body);
        } catch (IOException e) {
            throw new AuthenticationInfrastructureException(CREATE_BODY_FAILED);
        }
    }

    public void addRefreshTokenCookie(String refreshToken) {
        Cookie refreshTokenCookie = createRefreshTokenCookie(refreshToken);
        response.addCookie(refreshTokenCookie);
    }

    private Cookie createRefreshTokenCookie(String refreshToken) {
        Cookie cookie = new Cookie(REFRESH_TOKEN_NAME, refreshToken);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath(ROOT_PATH);
        cookie.setMaxAge(ONE_WEEK);
        return cookie;
    }
}
