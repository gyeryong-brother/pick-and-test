package com.gyeryongbrother.pickandtest.dataaccess.mapper;

import com.gyeryongbrother.pickandtest.dataaccess.entity.PortfolioEntity;
import com.gyeryongbrother.pickandtest.dataaccess.entity.PortfolioStockEntity;
import com.gyeryongbrother.pickandtest.dataaccess.entity.StockEntity;
import com.gyeryongbrother.pickandtest.domain.core.Portfolio;
import com.gyeryongbrother.pickandtest.domain.core.PortfolioStock;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PortfolioStockDataAccessMapper {

    private final StockDataAccessMapper stockDataAccessMapper;

    public PortfolioStockEntity portfolioStockToPortfolioStockEntity(PortfolioStock portfolioStock){
        PortfolioEntity portfolioEntity=PortfolioEntity.builder()
                .id(portfolioStock.getPortfolioId())
                .build();
        return PortfolioStockEntity.builder()
                .id(portfolioStock.getId())
                .portfolioEntity(portfolioEntity)
                .stockEntity(stockDataAccessMapper.stockToStockEntity(portfolioStock.getStock()))
                .portion(portfolioStock.getPortion())
                .build();
    }

    public PortfolioStock portfolioStockEntityToPortfolioStock(PortfolioStockEntity portfolioStockEntity){
        return PortfolioStock.builder()
                .id(portfolioStockEntity.getId())
                .portfolioId(portfolioStockEntity.getPortfolioEntity().getId())
                .stock(stockDataAccessMapper.stockEntityToStock(portfolioStockEntity.getStockEntity()))
                .portion(portfolioStockEntity.getPortion())
                .build();
    }
}
