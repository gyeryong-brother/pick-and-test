package com.gyeryongbrother.pickandtest.authentication.infrastructure.authentication.usernamepassword;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import com.gyeryongbrother.pickandtest.authentication.domain.core.entity.UsernamePasswordCredential;
import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.MemberRole;
import com.gyeryongbrother.pickandtest.authentication.domain.service.ports.output.UsernamePasswordCredentialQueryRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;

@ExtendWith(MockitoExtension.class)
class CustomUserDetailsServiceTest {

    @Mock
    private UsernamePasswordCredentialQueryRepository usernamePasswordCredentialQueryRepository;

    private CustomUserDetailsService customUserDetailsService;

    @BeforeEach
    void setUp() {
        customUserDetailsService = new CustomUserDetailsService(usernamePasswordCredentialQueryRepository);
    }

    @Test
    @DisplayName("회원을 불러온다")
    void loadUserByUsername() {
        // given
        UsernamePasswordCredential credential = new UsernamePasswordCredential(
                1L,
                1L,
                MemberRole.USER,
                "username",
                "password"
        );
        given(usernamePasswordCredentialQueryRepository.findByUsername("username"))
                .willReturn(Optional.of(credential));
        CustomUserDetails expected = new CustomUserDetails(credential);

        // when
        UserDetails result = customUserDetailsService.loadUserByUsername("username");

        // then
        assertThat(result).usingRecursiveComparison()
                .isEqualTo(expected);
    }
}
