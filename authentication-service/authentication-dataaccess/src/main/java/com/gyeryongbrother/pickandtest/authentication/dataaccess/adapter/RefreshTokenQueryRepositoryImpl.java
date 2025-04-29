package com.gyeryongbrother.pickandtest.authentication.dataaccess.adapter;

import static com.gyeryongbrother.pickandtest.authentication.dataaccess.entity.QRefreshTokenEntity.refreshTokenEntity;

import com.gyeryongbrother.pickandtest.authentication.dataaccess.entity.RefreshTokenEntity;
import com.gyeryongbrother.pickandtest.authentication.domain.core.entity.RefreshToken;
import com.gyeryongbrother.pickandtest.authentication.domain.service.ports.output.RefreshTokenQueryRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RefreshTokenQueryRepositoryImpl implements RefreshTokenQueryRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<RefreshToken> findByMemberId(Long memberId) {
        List<RefreshTokenEntity> refreshTokenEntities = queryFactory.selectFrom(refreshTokenEntity)
                .where(refreshTokenEntity.memberId.eq(memberId))
                .fetch();
        return refreshTokenEntities.stream()
                .map(RefreshTokenEntity::toDomain)
                .toList();
    }

    @Override
    public Optional<RefreshToken> findByToken(String token) {
        RefreshTokenEntity entity = queryFactory.selectFrom(refreshTokenEntity)
                .where(refreshTokenEntity.token.eq(token))
                .fetchOne();
        return Optional.ofNullable(entity)
                .map(RefreshTokenEntity::toDomain);
    }
}
