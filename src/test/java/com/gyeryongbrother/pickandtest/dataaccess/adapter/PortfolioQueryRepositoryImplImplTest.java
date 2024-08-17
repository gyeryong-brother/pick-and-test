package com.gyeryongbrother.pickandtest.dataaccess.adapter;

import static org.assertj.core.api.Assertions.assertThat;

import com.gyeryongbrother.pickandtest.dataaccess.config.TestQuerydslConfig;
import com.gyeryongbrother.pickandtest.dataaccess.mapper.PortfolioDataAccessMapper;
import com.gyeryongbrother.pickandtest.dataaccess.repository.PortfolioJpaRepository;
import com.gyeryongbrother.pickandtest.domain.core.Portfolio;
import com.gyeryongbrother.pickandtest.domain.core.Stock;
import com.gyeryongbrother.pickandtest.domain.service.ports.output.StockRepository;
import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stock.StockFixture;
import com.gyeryongbrother.pickandtest.member.domain.core.Member;
import com.gyeryongbrother.pickandtest.member.domain.service.ports.output.MemberRepository;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@DataJpaTest
@Import(TestQuerydslConfig.class)
@DisplayName("포트폴리오 조회 리퍼지터리 구현")
public class PortfolioQueryRepositoryImplImplTest {

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PortfolioJpaRepository portfolioJpaRepository;

    @Autowired
    private PortfolioDataAccessMapper portfolioDataAccessMapper;

    @Autowired
    private PortfolioQueryRepositoryImpl portfolioQueryRepositoryImpl;

    @Test
    @DisplayName("멤버 아이디로 해당 멤버의 모든 포트폴리오 조회")
    void findAllbyMemberId() {
        //given
        Stock apple = StockFixture.apple();
        Stock nvidia = StockFixture.nvidia();
        Stock savedApple = stockRepository.save(apple);
        Stock savedNvidia = stockRepository.save(nvidia);

        Member member1 = Member.builder().build();
        Member member2 = Member.builder().build();
        Member savedMember1 = memberRepository.save(member1);
        Member savedMember2 = memberRepository.save(member2);

        Portfolio portfolio1 = Portfolio.builder()
                .memberId(savedMember1.getId())
                .build();
        Portfolio portfolio2 = Portfolio.builder()
                .memberId(savedMember2.getId())
                .build();
        Portfolio portfolio3 = Portfolio.builder()
                .memberId(savedMember1.getId())
                .build();

        List<Portfolio> portfolios = List.of(
                portfolio1,
                portfolio2,
                portfolio3
        );

        portfolioJpaRepository.saveAll(
                portfolios.stream().map(portfolioDataAccessMapper::portfolioToPortfolioEntity).toList());

        List<Portfolio> expected = List.of(portfolio1, portfolio3);

        //when
        List<Portfolio> result = portfolioQueryRepositoryImpl.findAllByMemberId(savedMember1.getId());

        //then
        assertThat(result).usingRecursiveComparison()
                .ignoringExpectedNullFields()
                .isEqualTo(expected);
    }
}
