package com.gyeryongbrother.pickandtest.member.domain.service;

import com.gyeryongbrother.pickandtest.domain.core.Portfolio;
import com.gyeryongbrother.pickandtest.domain.service.dto.PortfolioResponse;
import com.gyeryongbrother.pickandtest.domain.service.ports.output.PortfolioQueryRepository;
import com.gyeryongbrother.pickandtest.member.domain.core.Member;
import com.gyeryongbrother.pickandtest.member.domain.service.dto.RegisterMemberCommand;
import com.gyeryongbrother.pickandtest.member.domain.service.dto.RegisterMemberResponse;
import com.gyeryongbrother.pickandtest.member.domain.service.ports.input.MemberService;
import com.gyeryongbrother.pickandtest.member.domain.service.ports.output.MemberRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final PortfolioQueryRepository portfolioQueryRepository;

    @Override
    public RegisterMemberResponse register(RegisterMemberCommand registerMemberCommand) {
        Member member = registerMemberCommand.toDomain();
        Member registeredMember = memberRepository.save(member);
        return RegisterMemberResponse.from(registeredMember);
    }

    @Override
    public List<PortfolioResponse> findAllPortfolios(Long memberId) {
        List<Portfolio> portfolios=portfolioQueryRepository.findAllByMemberId(memberId);
        return portfolios.stream().map(PortfolioResponse::from).toList();
    }
}
