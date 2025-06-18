package com.gyeryongbrother.pickandtest.gateway.security;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.security.config.web.server.ServerHttpSecurity.AuthorizeExchangeSpec;
import org.springframework.security.config.web.server.ServerHttpSecurity.OAuth2ResourceServerSpec;

public class ApiAuthorization {

    private static final String LOGOUT_API = "/authentication-service/auth/logout";
    private static final String AUTH_API = "/authentication-service/auth/**";

    public static void rule(AuthorizeExchangeSpec exchange) {
        exchange.pathMatchers(LOGOUT_API).authenticated()
                .pathMatchers(AUTH_API).permitAll()
                .anyExchange().authenticated();
    }

    public static void method(OAuth2ResourceServerSpec oAuth) {
        oAuth.jwt(withDefaults());
    }
}
