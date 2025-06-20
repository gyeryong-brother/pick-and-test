package com.gyeryongbrother.pickandtest.gateway.security;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.security.config.web.server.ServerHttpSecurity.AuthorizeExchangeSpec;
import org.springframework.security.config.web.server.ServerHttpSecurity.OAuth2ResourceServerSpec;

public class ApiAuthorization {

    private static final String LOGOUT_API = "/authentication-service/auth/logout";
    private static final String AUTH_API = "/authentication-service/auth/**";
    private static final String LOGIN_API = "/authentication-service/login";
    private static final String MEMBER_API = "/member-service/members";

    public static void rule(AuthorizeExchangeSpec exchange) {
        exchange.pathMatchers(LOGOUT_API).authenticated()
                .pathMatchers(LOGIN_API).permitAll()
                .pathMatchers(MEMBER_API).permitAll()
                .pathMatchers(AUTH_API).permitAll()
                .anyExchange().authenticated();
    }

    public static void method(OAuth2ResourceServerSpec oAuth) {
        oAuth.jwt(withDefaults());
    }
}
