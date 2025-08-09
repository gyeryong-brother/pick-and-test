package com.gyeryongbrother.pickandtest.gateway.security;

import java.util.Collection;
import java.util.List;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

public class CustomJwtAuthenticationConverter implements Converter<Jwt, AbstractAuthenticationToken> {

    @Override
    public AbstractAuthenticationToken convert(Jwt jwt) {
        Collection<SimpleGrantedAuthority> authorities = extractAuthorities(jwt);
        String principal = jwt.getSubject();
        return new JwtAuthenticationToken(jwt, authorities, principal);
    }

    private Collection<SimpleGrantedAuthority> extractAuthorities(Jwt jwt) {
        List<String> authorityList = jwt.getClaimAsStringList("authorities");
        if (authorityList == null) {
            return List.of();
        }
        return authorityList.stream()
                .map(SimpleGrantedAuthority::new)
                .toList();
    }
}
