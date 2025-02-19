package com.gyeryongbrother.pickandtest.member.dataaccess.adapter;

import com.gyeryongbrother.pickandtest.member.dataaccess.entity.MemberEntity;
import com.gyeryongbrother.pickandtest.member.dataaccess.mapper.MemberDataAccessMapper;
import com.gyeryongbrother.pickandtest.member.dataaccess.repository.MemberJpaRepository;
import com.gyeryongbrother.pickandtest.member.domain.core.Member;
import com.gyeryongbrother.pickandtest.member.domain.service.ports.output.MemberQueryRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberQueryRepositoryImpl implements MemberQueryRepository {

    private final MemberJpaRepository memberJpaRepository;
    private final MemberDataAccessMapper memberDataAccessMapper;

    @Override
    public Member findByUserId(String userId) {
        List<MemberEntity> memberEntities = memberJpaRepository.findByUserId(userId);
        if (memberEntities.isEmpty()) {
            throw new RuntimeException("userId is not exists");
        }
        MemberEntity memberEntity = memberEntities.get(0);
        return memberDataAccessMapper.memberEntityToMember(memberEntity);
    }
}
