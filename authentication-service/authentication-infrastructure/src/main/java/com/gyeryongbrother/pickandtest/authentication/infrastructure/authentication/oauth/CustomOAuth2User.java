package com.gyeryongbrother.pickandtest.authentication.infrastructure.authentication.oauth;

import com.gyeryongbrother.pickandtest.authentication.domain.core.entity.OAuthCredential;
import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.LoginType;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.authentication.jwt.Tokenizable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

@RequiredArgsConstructor
public class CustomOAuth2User implements OAuth2User, Tokenizable {

    private final OAuthCredential oauthCredential;
    private final OAuth2User oAuth2User;

    @Override
    public LoginType type() {
        return LoginType.OAUTH;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return oAuth2User.getAttributes();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String memberRole = oauthCredential.memberRole().name();
        return List.of(new SimpleGrantedAuthority(memberRole));
    }

    @Override
    public String getName() {
        return String.valueOf(oauthCredential.memberId());
    }

    @Override
    public String subject() {
        return String.valueOf(oauthCredential.memberId());
    }

    @Override
    public List<String> authorities() {
        return getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();
    }
}
