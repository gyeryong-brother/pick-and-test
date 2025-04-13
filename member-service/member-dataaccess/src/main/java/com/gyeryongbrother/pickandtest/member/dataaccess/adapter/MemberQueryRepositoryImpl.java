package com.gyeryongbrother.pickandtest.member.dataaccess.adapter;

import static com.gyeryongbrother.pickandtest.member.dataaccess.entity.QMemberEntity.memberEntity;
import static com.gyeryongbrother.pickandtest.member.domain.service.exception.MemberServiceExceptionType.USER_NONEXISTS;

import com.gyeryongbrother.pickandtest.member.dataaccess.entity.MemberEntity;
import com.gyeryongbrother.pickandtest.member.dataaccess.mapper.MemberDataAccessMapper;
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
    private final MemberJpaRepository memberJpaRepository;
    private final MemberDataAccessMapper memberDataAccessMapper;

    @Override
    public Member getByUsername(String username) {
        MemberEntity memberEntity1 = queryFactory.selectFrom(memberEntity)
                .where(memberEntity.username.eq(username))
                .fetchOne();
        return Optional.ofNullable(memberEntity1)
                .map(memberDataAccessMapper::memberEntityToMember)
                .orElseThrow(() -> new MemberServiceException(USER_NONEXISTS));
    }

    @Override
    public Optional<Member> findByUsername(String username) {
        Optional<MemberEntity> memberEntity1 = Optional.ofNullable(
                queryFactory.selectFrom(memberEntity)
                        .where(memberEntity.username.eq(username))
                        .fetchOne()
        );
        return memberEntity1.map(memberDataAccessMapper::memberEntityToMember);
    }

    @Override
    public Member findByMemberId(Long memberId) {
        MemberEntity memberEntity=memberJpaRepository.findById(memberId)
                .orElseThrow(() -> new MemberServiceException(USER_NONEXISTS));
        return memberDataAccessMapper.memberEntityToMember(memberEntity);
    }
}
