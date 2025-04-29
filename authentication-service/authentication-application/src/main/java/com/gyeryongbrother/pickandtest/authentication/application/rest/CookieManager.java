package com.gyeryongbrother.pickandtest.authentication.application.rest;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

@Component
public class CookieManager {

    private static final String REFRESH_TOKEN_NAME = "refresh-token";
    private static final String ROOT_PATH = "/";
    private static final int ONE_WEEK_IN_SECOND = 7 * 24 * 60 * 60;
    public void setCookie(HttpServletResponse response, String refreshToken) {
        Cookie cookie = new Cookie(REFRESH_TOKEN_NAME, refreshToken);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath(ROOT_PATH);
        cookie.setMaxAge(ONE_WEEK_IN_SECOND);
        response.addCookie(cookie);
    }
}
