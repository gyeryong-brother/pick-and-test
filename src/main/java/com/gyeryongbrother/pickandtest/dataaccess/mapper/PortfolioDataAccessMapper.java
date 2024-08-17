package com.gyeryongbrother.pickandtest.dataaccess.mapper;

import com.gyeryongbrother.pickandtest.dataaccess.entity.PortfolioEntity;
import com.gyeryongbrother.pickandtest.domain.core.Portfolio;
import com.gyeryongbrother.pickandtest.member.dataaccess.entity.MemberEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PortfolioDataAccessMapper {

    private final PortfolioStockDataAccessMapper portfolioStockDataAccessMapper;

    public PortfolioEntity portfolioToPortfolioEntity(Portfolio portfolio) {
        MemberEntity memberEntity=MemberEntity.builder()
                .id(portfolio.getMemberId())
                .build();
        return PortfolioEntity.builder()
                .id(portfolio.getId())
                .memberEntity(memberEntity)
                .build();
    }

    public Portfolio portfolioEntityToPortfolio(PortfolioEntity portfolioEntity) {
        return Portfolio.builder()
                .id(portfolioEntity.getId())
                .memberId(portfolioEntity.getMemberEntity().getId())
                .build();
    }
}
