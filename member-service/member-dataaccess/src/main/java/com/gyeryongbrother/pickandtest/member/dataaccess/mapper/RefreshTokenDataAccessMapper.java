package com.gyeryongbrother.pickandtest.member.dataaccess.mapper;

import com.gyeryongbrother.pickandtest.member.dataaccess.entity.RefreshTokenEntity;
import com.gyeryongbrother.pickandtest.member.domain.core.RefreshToken;
import org.springframework.stereotype.Component;

@Component
public class RefreshTokenDataAccessMapper {

    public RefreshTokenEntity refreshTokenToRefreshTokenEntity(RefreshToken refreshToken) {
        return RefreshTokenEntity.builder()
                .id(refreshToken.getId())
                .username(refreshToken.getUsername())
                .token(refreshToken.getToken())
                .build();
    }

    public RefreshToken refreshTokenEntityToRefreshToken(RefreshTokenEntity refreshTokenEntity) {
        return RefreshToken.builder()
                .id(refreshTokenEntity.getId())
                .username(refreshTokenEntity.getUsername())
                .token(refreshTokenEntity.getToken())
                .build();
    }
}
