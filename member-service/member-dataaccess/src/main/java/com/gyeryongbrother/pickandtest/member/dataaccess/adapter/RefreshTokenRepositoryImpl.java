package com.gyeryongbrother.pickandtest.member.dataaccess.adapter;

import com.gyeryongbrother.pickandtest.member.dataaccess.entity.RefreshTokenEntity;
import com.gyeryongbrother.pickandtest.member.dataaccess.mapper.RefreshTokenDataAccessMapper;
import com.gyeryongbrother.pickandtest.member.dataaccess.repository.RefreshTokenJpaRepository;
import com.gyeryongbrother.pickandtest.member.domain.core.RefreshToken;
import com.gyeryongbrother.pickandtest.member.domain.service.ports.output.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RefreshTokenRepositoryImpl implements RefreshTokenRepository {

    private final RefreshTokenJpaRepository refreshTokenJpaRepository;
    private final RefreshTokenDataAccessMapper refreshTokenDataAccessMapper;

    @Override
    public RefreshToken save(RefreshToken refreshToken) {
        RefreshTokenEntity refreshTokenEntity = refreshTokenDataAccessMapper.refreshTokenToRefreshTokenEntity(
                refreshToken);
        RefreshTokenEntity saved = refreshTokenJpaRepository.save(refreshTokenEntity);
        return refreshTokenDataAccessMapper.refreshTokenEntityToRefreshToken(saved);
    }
}
