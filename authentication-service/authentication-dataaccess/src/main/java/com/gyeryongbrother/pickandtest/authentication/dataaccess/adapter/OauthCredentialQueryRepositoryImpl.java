package com.gyeryongbrother.pickandtest.authentication.dataaccess.adapter;

import static com.gyeryongbrother.pickandtest.authentication.dataaccess.entity.QOauthCredentialEntity.oauthCredentialEntity;

import com.gyeryongbrother.pickandtest.authentication.dataaccess.entity.OauthCredentialEntity;
import com.gyeryongbrother.pickandtest.authentication.domain.core.entity.OauthCredential;
import com.gyeryongbrother.pickandtest.authentication.domain.service.ports.output.OauthCredentialQueryRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OauthCredentialQueryRepositoryImpl implements OauthCredentialQueryRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<OauthCredential> findByOauthId(String oauthId) {
        OauthCredentialEntity entity = queryFactory.selectFrom(oauthCredentialEntity)
                .where(oauthCredentialEntity.oauthId.eq(oauthId))
                .fetchOne();
        return Optional.ofNullable(entity)
                .map(OauthCredentialEntity::toDomain);
    }
}
