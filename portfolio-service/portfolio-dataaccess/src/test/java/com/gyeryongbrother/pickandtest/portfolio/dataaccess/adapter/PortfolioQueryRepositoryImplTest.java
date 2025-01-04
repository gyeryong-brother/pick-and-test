package com.gyeryongbrother.pickandtest.portfolio.dataaccess.adapter;

import static org.assertj.core.api.Assertions.assertThat;

import com.gyeryongbrother.pickandtest.portfolio.dataaccess.config.TestQuerydslConfig;
import com.gyeryongbrother.pickandtest.portfolio.dataaccess.mapper.PortfolioDataAccessMapper;
import com.gyeryongbrother.pickandtest.portfolio.dataaccess.repository.PortfolioJpaRepository;
import com.gyeryongbrother.pickandtest.portfolio.domain.core.entity.Portfolio;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@DataJpaTest
@Import(TestQuerydslConfig.class)
@DisplayName("포트폴리오 조회 리퍼지터리 구현")
class PortfolioQueryRepositoryImplTest {

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
        Portfolio portfolio1 = Portfolio.builder()
                .memberId(1L)
                .build();
        Portfolio portfolio2 = Portfolio.builder()
                .memberId(2L)
                .build();
        Portfolio portfolio3 = Portfolio.builder()
                .memberId(1L)
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
        List<Portfolio> result = portfolioQueryRepositoryImpl.findAllByMemberId(1L);

        //then
        assertThat(result).usingRecursiveComparison()
                .ignoringExpectedNullFields()
                .isEqualTo(expected);
    }
}
