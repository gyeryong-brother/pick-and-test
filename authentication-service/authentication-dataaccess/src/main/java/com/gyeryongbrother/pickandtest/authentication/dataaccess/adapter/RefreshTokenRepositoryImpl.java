package com.gyeryongbrother.pickandtest.authentication.dataaccess.adapter;

import com.gyeryongbrother.pickandtest.authentication.dataaccess.entity.RefreshTokenEntity;
import com.gyeryongbrother.pickandtest.authentication.dataaccess.mapper.RefreshTokenDataAccessMapper;
import com.gyeryongbrother.pickandtest.authentication.dataaccess.repository.RefreshTokenJpaRepository;
import com.gyeryongbrother.pickandtest.authentication.domain.core.entity.RefreshToken;
import com.gyeryongbrother.pickandtest.authentication.domain.service.ports.output.RefreshTokenRepository;
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
        return saved.toDomain();
    }

    @Override
    public void deleteByToken(String refreshToken) {
        refreshTokenJpaRepository.deleteByToken(refreshToken);
    }
}
