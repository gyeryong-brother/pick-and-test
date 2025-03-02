package com.gyeryongbrother.pickandtest.member.dataaccess.adapter;

import com.gyeryongbrother.pickandtest.member.dataaccess.entity.RefreshTokenEntity;
import com.gyeryongbrother.pickandtest.member.dataaccess.mapper.RefreshTokenDataAccessMapper;
import com.gyeryongbrother.pickandtest.member.dataaccess.repository.RefreshTokenJpaRepository;
import com.gyeryongbrother.pickandtest.member.domain.core.RefreshToken;
import com.gyeryongbrother.pickandtest.member.domain.service.ports.output.RefreshTokenQueryRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RefreshTokenQueryRepositoryImpl implements RefreshTokenQueryRepository {

    private final RefreshTokenJpaRepository refreshTokenJpaRepository;
    private final RefreshTokenDataAccessMapper refreshTokenDataAccessMapper;

    @Override
    public List<RefreshToken> findByUsername(String username) {
        List<RefreshTokenEntity> refreshTokenEntities=refreshTokenJpaRepository.findByUsername(username);
        return refreshTokenEntities.stream()
                .map(refreshTokenDataAccessMapper::refreshTokenEntityToRefreshToken)
                .toList();
    }
}
