package com.gyeryongbrother.pickandtest.authentication.application.rest;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

@Component
public class CookieManager {

    private static final String REFRESH_TOKEN_NAME = "refresh-token";
    private static final String ROOT_PATH = "/";
    private static final int ZERO = 0;

    public void deleteCookie(HttpServletResponse response) {
        Cookie cookie = new Cookie(REFRESH_TOKEN_NAME, null);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath(ROOT_PATH);
        cookie.setMaxAge(ZERO);
        response.addCookie(cookie);
    }
}
