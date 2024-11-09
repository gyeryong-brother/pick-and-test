package com.gyeryongbrother.pickandtest.dataaccess.adapter;

import com.gyeryongbrother.pickandtest.dataaccess.entity.PortfolioStockEntity;
import com.gyeryongbrother.pickandtest.dataaccess.mapper.PortfolioStockDataAccessMapper;
import com.gyeryongbrother.pickandtest.dataaccess.repository.PortfolioStockJpaRepository;
import com.gyeryongbrother.pickandtest.domain.core.PortfolioStock;
import com.gyeryongbrother.pickandtest.domain.service.ports.output.PortfolioStockRepository;
import java.util.List;
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

    @Override
    public void deleteAllByPortfolioId(Long portfolioId) {
        portfolioStockJpaRepository.deleteAllByPortfolioEntityId(portfolioId);
    }

    @Override
    public List<PortfolioStock> saveAll(List<PortfolioStock> portfolioStocks) {
        List<PortfolioStockEntity> portfolioStockEntities = portfolioStocks.stream()
                .map(portfolioStockDataAccessMapper::portfolioStockToPortfolioStockEntity)
                .toList();
        List<PortfolioStockEntity> savedPortfolioStockEntities =
                portfolioStockJpaRepository.saveAll(portfolioStockEntities);
        return savedPortfolioStockEntities.stream()
                .map(portfolioStockDataAccessMapper::portfolioStockEntityToPortfolioStock)
                .toList();
    }
}
