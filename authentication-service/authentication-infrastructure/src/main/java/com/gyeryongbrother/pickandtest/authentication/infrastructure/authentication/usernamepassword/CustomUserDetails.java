package com.gyeryongbrother.pickandtest.authentication.infrastructure.authentication.usernamepassword;

import com.gyeryongbrother.pickandtest.authentication.domain.core.entity.UsernamePasswordCredential;
import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.LoginType;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.authentication.jwt.Tokenizable;
import java.util.Collection;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@RequiredArgsConstructor
public class CustomUserDetails implements UserDetails, Tokenizable {

    private final UsernamePasswordCredential usernamePasswordCredential;

    @Override
    public LoginType type() {
        return LoginType.USERNAME_PASSWORD;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(
                new SimpleGrantedAuthority(usernamePasswordCredential.memberRole().name())
        );
    }

    @Override
    public String getPassword() {
        return usernamePasswordCredential.password();
    }

    @Override
    public String getUsername() {
        return usernamePasswordCredential.username();
    }

    @Override
    public String subject() {
        return String.valueOf(usernamePasswordCredential.memberId());
    }

    @Override
    public List<String> authorities() {
        return getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();
    }
}
