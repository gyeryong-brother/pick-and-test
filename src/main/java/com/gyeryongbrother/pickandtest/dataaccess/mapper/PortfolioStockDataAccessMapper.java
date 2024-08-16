package com.gyeryongbrother.pickandtest.dataaccess.mapper;

import com.gyeryongbrother.pickandtest.dataaccess.entity.PortfolioEntity;
import com.gyeryongbrother.pickandtest.dataaccess.entity.PortfolioStockEntity;
import com.gyeryongbrother.pickandtest.dataaccess.entity.StockEntity;
import com.gyeryongbrother.pickandtest.domain.core.PortfolioStock;
import org.springframework.stereotype.Component;

@Component
public class PortfolioStockDataAccessMapper {

    public PortfolioStockEntity portfolioStockToPortfolioStockEntity(PortfolioStock portfolioStock){
        PortfolioEntity portfolioEntity=PortfolioEntity.builder()
                .id(portfolioStock.getPortfolioId())
                .build();
        StockEntity stockEntity=StockEntity.builder()
                .id(portfolioStock.getStockId())
                .build();
        return PortfolioStockEntity.builder()
                .id(portfolioStock.getId())
                .portfolioEntity(portfolioEntity)
                .stockEntity(stockEntity)
                .portion(portfolioStock.getPortion())
                .build();
    }

    public PortfolioStock portfolioStockEntityToPortfolioStock(PortfolioStockEntity portfolioStockEntity){
        return PortfolioStock.builder()
                .id(portfolioStockEntity.getId())
                .portfolioId(portfolioStockEntity.getPortfolioEntity().getId())
                .stockId(portfolioStockEntity.getStockEntity().getId())
                .portion(portfolioStockEntity.getPortion())
                .build();
    }
}
