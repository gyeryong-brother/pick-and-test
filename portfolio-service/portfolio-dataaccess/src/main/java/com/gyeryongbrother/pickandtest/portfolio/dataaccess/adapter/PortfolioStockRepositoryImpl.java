package com.gyeryongbrother.pickandtest.portfolio.dataaccess.adapter;

import com.gyeryongbrother.pickandtest.portfolio.dataaccess.entity.PortfolioStockEntity;
import com.gyeryongbrother.pickandtest.portfolio.dataaccess.mapper.PortfolioStockDataAccessMapper;
import com.gyeryongbrother.pickandtest.portfolio.dataaccess.repository.PortfolioStockJpaRepository;
import com.gyeryongbrother.pickandtest.portfolio.domain.core.entity.PortfolioStock;
import com.gyeryongbrother.pickandtest.portfolio.domain.service.ports.output.PortfolioStockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PortfolioStockRepositoryImpl implements PortfolioStockRepository {

    private final PortfolioStockJpaRepository portfolioStockJpaRepository;
    private final PortfolioStockDataAccessMapper portfolioStockDataAccessMapper;

    @Override
    public PortfolioStock save(PortfolioStock portfolioStock) {
        PortfolioStockEntity portfolioStockEntity =
                portfolioStockDataAccessMapper.portfolioStockToPortfolioStockEntity(portfolioStock);
        PortfolioStockEntity saved = portfolioStockJpaRepository.save(portfolioStockEntity);
        return portfolioStockDataAccessMapper.portfolioStockEntityToPortfolioStock(saved);
    }
}
