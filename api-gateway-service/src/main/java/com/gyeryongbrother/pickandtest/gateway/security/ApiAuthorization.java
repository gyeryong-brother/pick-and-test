package com.gyeryongbrother.pickandtest.gateway.security;

import org.springframework.security.config.web.server.ServerHttpSecurity.AuthorizeExchangeSpec;

public class ApiAuthorization {

    public static void rule(AuthorizeExchangeSpec exchange) {
        exchange.anyExchange().permitAll();
    }
}
