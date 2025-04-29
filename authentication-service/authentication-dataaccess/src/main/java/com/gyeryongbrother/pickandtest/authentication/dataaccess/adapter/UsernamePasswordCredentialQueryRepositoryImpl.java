package com.gyeryongbrother.pickandtest.authentication.dataaccess.adapter;

import static com.gyeryongbrother.pickandtest.authentication.dataaccess.entity.QUsernamePasswordCredentialEntity.usernamePasswordCredentialEntity;

import com.gyeryongbrother.pickandtest.authentication.dataaccess.entity.UsernamePasswordCredentialEntity;
import com.gyeryongbrother.pickandtest.authentication.domain.core.entity.UsernamePasswordCredential;
import com.gyeryongbrother.pickandtest.authentication.domain.service.ports.output.UsernamePasswordCredentialQueryRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UsernamePasswordCredentialQueryRepositoryImpl implements UsernamePasswordCredentialQueryRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<UsernamePasswordCredential> findByUsername(String username) {
        UsernamePasswordCredentialEntity entity = queryFactory.selectFrom(
                        usernamePasswordCredentialEntity)
                .where(usernamePasswordCredentialEntity.username.eq(username))
                .fetchOne();
        return Optional.ofNullable(entity)
                .map(UsernamePasswordCredentialEntity::toDomain);
    }
}
