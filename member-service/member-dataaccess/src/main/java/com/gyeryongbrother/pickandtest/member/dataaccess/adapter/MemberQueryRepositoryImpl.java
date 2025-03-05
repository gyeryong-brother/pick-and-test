package com.gyeryongbrother.pickandtest.member.dataaccess.adapter;

import static com.gyeryongbrother.pickandtest.member.domain.service.exception.MemberServiceExceptionType.USER_NONEXISTS;
import static com.gyeryongbrother.pickandtest.member.dataaccess.entity.QMemberEntity.memberEntity;

import com.gyeryongbrother.pickandtest.member.dataaccess.entity.MemberEntity;
import com.gyeryongbrother.pickandtest.member.dataaccess.entity.QMemberEntity;
import com.gyeryongbrother.pickandtest.member.dataaccess.mapper.MemberDataAccessMapper;
import com.gyeryongbrother.pickandtest.member.dataaccess.repository.MemberJpaRepository;
import com.gyeryongbrother.pickandtest.member.domain.core.Member;
import com.gyeryongbrother.pickandtest.member.domain.service.exception.MemberServiceException;
import com.gyeryongbrother.pickandtest.member.domain.service.ports.output.MemberQueryRepository;
import com.querydsl.core.QueryFactory;
import com.querydsl.core.types.EntityPath;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class MemberQueryRepositoryImpl implements MemberQueryRepository {

    private final JPAQueryFactory queryFactory;
    private final MemberJpaRepository memberJpaRepository;
    private final MemberDataAccessMapper memberDataAccessMapper;

    @Override
    public Member findByUsername(String username) {
        /*List<MemberEntity> memberEntities = memberJpaRepository.findByUsername(username);
        if (memberEntities.isEmpty()) {
            throw new MemberServiceException(USER_NONEXISTS);
        }
        MemberEntity memberEntity = memberEntities.get(0);
        return memberDataAccessMapper.memberEntityToMember(memberEntity);*/

        MemberEntity memberEntity1 = queryFactory.selectFrom(memberEntity)
                .where(memberEntity.username.eq(username))
                .fetchOne();
        return Optional.ofNullable(memberEntity1)
                .map(memberDataAccessMapper::memberEntityToMember)
                .orElseThrow(() -> new MemberServiceException(USER_NONEXISTS));
    }
}
