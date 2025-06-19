package com.gyeryongbrother.pickandtest.authentication.dataaccess.adapter;

import static com.gyeryongbrother.pickandtest.authentication.dataaccess.entity.QOauthCredentialEntity.oauthCredentialEntity;

import com.gyeryongbrother.pickandtest.authentication.dataaccess.entity.OauthCredentialEntity;
import com.gyeryongbrother.pickandtest.authentication.domain.core.entity.OauthCredential;
import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.OAuthId;
import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.OAuthType;
import com.gyeryongbrother.pickandtest.authentication.domain.service.ports.output.OauthCredentialQueryRepository;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OauthCredentialQueryRepositoryImpl implements OauthCredentialQueryRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<OauthCredential> findByOauthId(OAuthId oauthId) {
        OauthCredentialEntity entity = queryFactory.selectFrom(oauthCredentialEntity)
                .where(oauthIdEq(oauthId))
                .fetchOne();
        return Optional.ofNullable(entity)
                .map(OauthCredentialEntity::toDomain);
    }

    private BooleanExpression oauthIdEq(OAuthId oAuthId) {
        OAuthType oAuthType = oAuthId.oAuthType();
        return oauthServerIdEq(oAuthId.value())
                .and(authenticationMethodEq(oAuthType));
    }

    private BooleanExpression oauthServerIdEq(String oAuthId) {
        return oauthCredentialEntity.oAuthId.eq(oAuthId);
    }

    private BooleanExpression authenticationMethodEq(OAuthType oAuthType) {
        return oauthCredentialEntity.oAuthType.eq(oAuthType);
    }
}
