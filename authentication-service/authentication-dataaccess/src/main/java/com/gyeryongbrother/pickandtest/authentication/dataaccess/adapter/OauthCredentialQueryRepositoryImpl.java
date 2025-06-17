package com.gyeryongbrother.pickandtest.authentication.dataaccess.adapter;

import static com.gyeryongbrother.pickandtest.authentication.dataaccess.entity.QOauthCredentialEntity.oauthCredentialEntity;

import com.gyeryongbrother.pickandtest.authentication.dataaccess.entity.OauthCredentialEntity;
import com.gyeryongbrother.pickandtest.authentication.domain.core.entity.OauthCredential;
import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.AuthenticationMethod;
import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.OauthId;
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
    public Optional<OauthCredential> findByOauthId(OauthId oauthId) {
        OauthCredentialEntity entity = queryFactory.selectFrom(oauthCredentialEntity)
                .where(oauthIdEq(oauthId))
                .fetchOne();
        return Optional.ofNullable(entity)
                .map(OauthCredentialEntity::toDomain);
    }

    private BooleanExpression oauthIdEq(OauthId oauthId) {
        String oauthServerId = oauthId.oauthServerId();
        AuthenticationMethod authenticationMethod = oauthId.authenticationMethod();
        return oauthServerIdEq(oauthServerId)
                .and(authenticationMethodEq(authenticationMethod));
    }

    private BooleanExpression oauthServerIdEq(String oauthServerId) {
        return oauthCredentialEntity.oauthServerId.eq(oauthServerId);
    }

    private BooleanExpression authenticationMethodEq(AuthenticationMethod authenticationMethod) {
        return oauthCredentialEntity.authenticationMethod.eq(authenticationMethod);
    }
}
