package com.gyeryongbrother.pickandtest.gateway.security;

import java.util.List;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Component
public class GuestAuthenticationFilter implements WebFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        return ReactiveSecurityContextHolder.getContext()
                .flatMap(context -> chain.filter(exchange))
                .switchIfEmpty(Mono.defer(() -> {
                    List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("GUEST"));
                    Authentication guestAuth = new UsernamePasswordAuthenticationToken("anonymous", null, authorities);
                    SecurityContext guestContext = new SecurityContextImpl(guestAuth);

                    return chain.filter(exchange)
                            .contextWrite(ReactiveSecurityContextHolder.withSecurityContext(Mono.just(guestContext)));
                }));
    }
}
