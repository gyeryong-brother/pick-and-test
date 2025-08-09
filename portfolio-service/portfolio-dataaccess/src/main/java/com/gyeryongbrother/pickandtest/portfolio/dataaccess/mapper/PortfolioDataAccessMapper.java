package com.gyeryongbrother.pickandtest.portfolio.dataaccess.mapper;

import com.gyeryongbrother.pickandtest.portfolio.dataaccess.entity.PortfolioEntity;
import com.gyeryongbrother.pickandtest.portfolio.dataaccess.entity.PortfolioStockEntity;
import com.gyeryongbrother.pickandtest.portfolio.domain.core.entity.Portfolio;
import com.gyeryongbrother.pickandtest.portfolio.domain.core.entity.PortfolioStock;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PortfolioDataAccessMapper {

    private final PortfolioStockDataAccessMapper portfolioStockDataAccessMapper;

    public PortfolioEntity portfolioToPortfolioEntity(Portfolio portfolio) {
        List<PortfolioStockEntity> portfolioStockEntities = portfolio.getPortfolioStocks().stream()
                .map(portfolioStockDataAccessMapper::portfolioStockToPortfolioStockEntity)
                .toList();
        return PortfolioEntity.builder()
                .id(portfolio.getId())
                .memberId(portfolio.getMemberId())
                .portfolioStockEntities(portfolioStockEntities)
                .build();
    }

    public Portfolio portfolioEntityToPortfolio(PortfolioEntity portfolioEntity) {
        List<PortfolioStock> portfolioStocks = portfolioEntity.getPortfolioStockEntities().stream()
                .map(portfolioStockDataAccessMapper::portfolioStockEntityToPortfolioStock)
                .toList();
        return Portfolio.builder()
                .id(portfolioEntity.getId())
                .memberId(portfolioEntity.getMemberId())
                .portfolioStocks(portfolioStocks)
                .build();
    }
}
