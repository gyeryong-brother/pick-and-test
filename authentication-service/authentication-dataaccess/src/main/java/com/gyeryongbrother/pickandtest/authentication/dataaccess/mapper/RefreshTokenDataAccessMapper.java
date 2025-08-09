package com.gyeryongbrother.pickandtest.authentication.dataaccess.mapper;

import com.gyeryongbrother.pickandtest.authentication.dataaccess.entity.RefreshTokenEntity;
import com.gyeryongbrother.pickandtest.authentication.domain.core.entity.RefreshToken;
import org.springframework.stereotype.Component;

@Component
public class RefreshTokenDataAccessMapper {

    public RefreshTokenEntity refreshTokenToRefreshTokenEntity(RefreshToken refreshToken) {
        return RefreshTokenEntity.builder()
                .id(refreshToken.id())
                .memberId(refreshToken.memberId())
                .token(refreshToken.token())
                .build();
    }
}
