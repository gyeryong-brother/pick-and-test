package com.gyeryongbrother.pickandtest.member.domain.service;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.gyeryongbrother.pickandtest.member.domain.core.UserRole;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@DisplayName("JwtUtil 기능을 실행한다")
public class JwtUtilTest {

    private JwtUtil jwtUtil=new JwtUtil();

    @Test
    @DisplayName("AccessToken을 생성한다")
    void generateAccessToken(){
        String accessToken=jwtUtil.generateAccessToken(1L, UserRole.ROLE_USER);
        UserRole role=jwtUtil.getRoleFromToken(accessToken);
        Long memberId=jwtUtil.getMemberIdFromToken(accessToken);
        assertAll(
                () -> assertThat(role).isEqualTo(UserRole.ROLE_USER),
                () -> assertThat(memberId).isEqualTo(1L)
        );
    }

    @Test
    @DisplayName("refreshToken을 생성한다")
    void generateRefreshToken(){
        String refreshToken= jwtUtil.generateRefreshToken(1L);
        Long memberId=jwtUtil.getMemberIdFromToken(refreshToken);
        assertThat(memberId).isEqualTo(1L);
    }
}
