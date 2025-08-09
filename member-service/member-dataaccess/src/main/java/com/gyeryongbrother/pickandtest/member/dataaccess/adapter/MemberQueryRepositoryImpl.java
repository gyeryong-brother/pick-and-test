package com.gyeryongbrother.pickandtest.member.dataaccess.adapter;

import static com.gyeryongbrother.pickandtest.member.domain.service.exception.MemberServiceExceptionType.USER_NONEXISTS;

import com.gyeryongbrother.pickandtest.member.dataaccess.entity.MemberEntity;
import com.gyeryongbrother.pickandtest.member.dataaccess.entity.QMemberEntity;
import com.gyeryongbrother.pickandtest.member.dataaccess.repository.MemberJpaRepository;
import com.gyeryongbrother.pickandtest.member.domain.core.Member;
import com.gyeryongbrother.pickandtest.member.domain.service.exception.MemberServiceException;
import com.gyeryongbrother.pickandtest.member.domain.service.ports.output.MemberQueryRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberQueryRepositoryImpl implements MemberQueryRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Member findByMemberId(Long memberId) {
        MemberEntity memberEntity = queryFactory.selectFrom(QMemberEntity.memberEntity)
                .where(QMemberEntity.memberEntity.id.eq(memberId))
                .fetchOne();
        return Optional.ofNullable(memberEntity)
                .map(MemberEntity::toDomain)
                .orElseThrow(() -> new MemberServiceException(USER_NONEXISTS));
    }
}
