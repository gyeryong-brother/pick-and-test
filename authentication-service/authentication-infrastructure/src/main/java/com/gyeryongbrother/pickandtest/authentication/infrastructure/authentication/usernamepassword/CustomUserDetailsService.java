package com.gyeryongbrother.pickandtest.authentication.infrastructure.authentication.usernamepassword;

import static com.gyeryongbrother.pickandtest.authentication.infrastructure.exception.AuthenticationInfrastructureExceptionType.USERNAME_PASSWORD_NOT_MATCH;

import com.gyeryongbrother.pickandtest.authentication.domain.core.entity.UsernamePasswordCredential;
import com.gyeryongbrother.pickandtest.authentication.domain.service.ports.output.UsernamePasswordCredentialQueryRepository;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.exception.AuthenticationInfrastructureException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CustomUserDetailsService implements UserDetailsService {

    private final UsernamePasswordCredentialQueryRepository usernamePasswordCredentialQueryRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        UsernamePasswordCredential credential = usernamePasswordCredentialQueryRepository.findByUsername(username)
                .orElseThrow(() -> new AuthenticationInfrastructureException(USERNAME_PASSWORD_NOT_MATCH));
        return new CustomUserDetails(credential);
    }
}
