package com.gyeryongbrother.pickandtest.member.dataaccess.adapter;

import com.gyeryongbrother.pickandtest.member.dataaccess.entity.MemberEntity;
import com.gyeryongbrother.pickandtest.member.dataaccess.mapper.MemberDataAccessMapper;
import com.gyeryongbrother.pickandtest.member.dataaccess.repository.MemberJpaRepository;
import com.gyeryongbrother.pickandtest.member.domain.core.Member;
import com.gyeryongbrother.pickandtest.member.domain.service.ports.output.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepository {

    private final MemberJpaRepository memberJpaRepository;
    private final MemberDataAccessMapper memberDataAccessMapper;

    @Override
    public Member save(Member member) {
        MemberEntity memberEntity = memberDataAccessMapper.memberToMemberEntity(member);
        MemberEntity savedMemberEntity = memberJpaRepository.save(memberEntity);
        return memberDataAccessMapper.memberEntityToMember(savedMemberEntity);
    }
}
