package com.gyeryongbrother.pickandtest.authentication.infrastructure.jwt;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.MemberRole;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class JwtProviderImplTest {

    @Test
    @DisplayName("AccessToken을 생성한다")
    void generateAccessToken() {
        JwtProvider jwtProvider = new JwtProviderImpl();
        String accessToken = jwtProvider.generateAccessToken(1L, MemberRole.USER);
        MemberRole role = jwtProvider.getRoleFromToken(accessToken);
        Long memberId = jwtProvider.getMemberIdFromToken(accessToken);
        assertAll(
                () -> assertThat(role).isEqualTo(MemberRole.USER),
                () -> assertThat(memberId).isEqualTo(1L)
        );
    }

    @Test
    @DisplayName("refreshToken을 생성한다")
    void generateRefreshToken() {
        JwtProvider jwtProvider = new JwtProviderImpl();
        String refreshToken = jwtProvider.generateRefreshToken(1L, MemberRole.USER);
        Long memberId = jwtProvider.getMemberIdFromToken(refreshToken);
        assertThat(memberId).isEqualTo(1L);
    }
}
