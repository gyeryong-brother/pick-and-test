package com.gyeryongbrother.pickandtest.member.dataaccess.mapper;

import com.gyeryongbrother.pickandtest.member.dataaccess.entity.RefreshTokenEntity;
import com.gyeryongbrother.pickandtest.member.domain.core.RefreshToken;
import org.springframework.stereotype.Component;

@Component
public class RefreshTokenDataAccessMapper {

    public RefreshTokenEntity refreshTokenToRefreshTokenEntity(RefreshToken refreshToken){
        return RefreshTokenEntity.builder()
                .username(refreshToken.getUsername())
                .refreshToken(refreshToken.getRefreshToken())
                .build();
    }

    public RefreshToken refreshTokenEntityToRefreshToken(RefreshTokenEntity refreshTokenEntity){
        return RefreshToken.builder()
                .username(refreshTokenEntity.getUsername())
                .refreshToken(refreshTokenEntity.getRefreshToken())
                .build();
    }
}
