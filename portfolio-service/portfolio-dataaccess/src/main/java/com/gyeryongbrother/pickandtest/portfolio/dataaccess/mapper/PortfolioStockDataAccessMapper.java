package com.gyeryongbrother.pickandtest.portfolio.dataaccess.mapper;

import com.gyeryongbrother.pickandtest.portfolio.dataaccess.entity.PortfolioEntity;
import com.gyeryongbrother.pickandtest.portfolio.dataaccess.entity.PortfolioStockEntity;
import com.gyeryongbrother.pickandtest.portfolio.domain.core.entity.PortfolioStock;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PortfolioStockDataAccessMapper {

    public PortfolioStockEntity portfolioStockToPortfolioStockEntity(PortfolioStock portfolioStock) {
        PortfolioEntity portfolioEntity = PortfolioEntity.builder()
                .id(portfolioStock.getPortfolioId())
                .build();
        return PortfolioStockEntity.builder()
                .id(portfolioStock.getId())
                .portfolioEntity(portfolioEntity)
                .stockId(portfolioStock.getStockId())
                .portion(portfolioStock.getPortion())
                .build();
    }

    public PortfolioStock portfolioStockEntityToPortfolioStock(PortfolioStockEntity portfolioStockEntity) {
        return PortfolioStock.builder()
                .id(portfolioStockEntity.getId())
                .portfolioId(portfolioStockEntity.getPortfolioEntity().getId())
                .stockId(portfolioStockEntity.getStockId())
                .portion(portfolioStockEntity.getPortion())
                .build();
    }
}
