package com.gyeryongbrother.pickandtest.dataaccess.mapper;

import com.gyeryongbrother.pickandtest.dataaccess.entity.PortfolioEntity;
import com.gyeryongbrother.pickandtest.domain.core.Portfolio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PortfolioDataAccessMapper {

    private final PortfolioStockDataAccessMapper portfolioStockDataAccessMapper;

    public PortfolioEntity portfolioToPortfolioEntity(Portfolio portfolio) {
        return PortfolioEntity.builder()
                .id(portfolio.getId())
                .memberId(portfolio.getMemberId())
                .build();
    }

    public Portfolio portfolioEntityToPortfolio(PortfolioEntity portfolioEntity) {
        return Portfolio.builder()
                .id(portfolioEntity.getId())
                .memberId(portfolioEntity.getMemberId())
                .build();
    }
}
