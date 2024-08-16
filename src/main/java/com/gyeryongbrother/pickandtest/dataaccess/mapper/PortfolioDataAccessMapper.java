package com.gyeryongbrother.pickandtest.dataaccess.mapper;

import com.gyeryongbrother.pickandtest.dataaccess.entity.PortfolioEntity;
import com.gyeryongbrother.pickandtest.dataaccess.entity.PortfolioStockEntity;
import com.gyeryongbrother.pickandtest.domain.core.Portfolio;
import com.gyeryongbrother.pickandtest.domain.core.PortfolioStock;
import java.util.List;

public class PortfolioDataAccessMapper {

    public PortfolioEntity portfolioToPortfolioEntity(Portfolio portfolio){
        List<PortfolioStockEntity> portfolioStockEntities=portfolio.getPortfolioStocks().stream()
                .map(PortfolioStockDataAccessMapper::portfolioStockToPortfolioStockEntity).toList();
        return PortfolioEntity.builder()
                .id(portfolio.getId())
                .portfolioStockEntities(portfolioStockEntities)
                .build();
    }

    public Portfolio portfolioEntityToPortfolio(PortfolioEntity portfolioEntity){
        List<PortfolioStock> portfolioStocks=portfolioEntity.getPortfolioStockEntities().stream()
                .map(PortfolioStockDataAccessMapper::portfolioStockEntityToPortfolioStock).toList();
        return Portfolio.builder()
                .id(portfolioEntity.getId())
                .portfolioStocks(portfolioStocks)
                .build();
    }
}
