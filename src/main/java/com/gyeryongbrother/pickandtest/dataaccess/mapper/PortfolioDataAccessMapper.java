package com.gyeryongbrother.pickandtest.dataaccess.mapper;

import com.gyeryongbrother.pickandtest.dataaccess.entity.PortfolioEntity;
import com.gyeryongbrother.pickandtest.dataaccess.entity.PortfolioStockEntity;
import com.gyeryongbrother.pickandtest.domain.core.Portfolio;
import com.gyeryongbrother.pickandtest.domain.core.PortfolioStock;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PortfolioDataAccessMapper {

    private final PortfolioStockDataAccessMapper portfolioStockDataAccessMapper;

    public PortfolioEntity portfolioToPortfolioEntity(Portfolio portfolio){
        List<PortfolioStockEntity> portfolioStockEntities=portfolio.getPortfolioStocks().stream()
                .map(portfolioStockDataAccessMapper::portfolioStockToPortfolioStockEntity).toList();
        return PortfolioEntity.builder()
                .id(portfolio.getId())
                .portfolioStockEntities(portfolioStockEntities)
                .build();
    }

    public Portfolio portfolioEntityToPortfolio(PortfolioEntity portfolioEntity){
        List<PortfolioStock> portfolioStocks=portfolioEntity.getPortfolioStockEntities().stream()
                .map(portfolioStockDataAccessMapper::portfolioStockEntityToPortfolioStock).toList();
        return Portfolio.builder()
                .id(portfolioEntity.getId())
                .portfolioStocks(portfolioStocks)
                .build();
    }
}
