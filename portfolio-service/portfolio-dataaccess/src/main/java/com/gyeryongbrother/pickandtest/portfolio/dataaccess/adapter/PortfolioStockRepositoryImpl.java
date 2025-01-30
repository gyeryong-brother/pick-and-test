package com.gyeryongbrother.pickandtest.portfolio.dataaccess.adapter;

import com.gyeryongbrother.pickandtest.portfolio.dataaccess.entity.PortfolioStockEntity;
import com.gyeryongbrother.pickandtest.portfolio.dataaccess.mapper.PortfolioStockDataAccessMapper;
import com.gyeryongbrother.pickandtest.portfolio.dataaccess.repository.PortfolioJpaRepository;
import com.gyeryongbrother.pickandtest.portfolio.dataaccess.repository.PortfolioStockJpaRepository;
import com.gyeryongbrother.pickandtest.portfolio.domain.core.entity.PortfolioStock;
import com.gyeryongbrother.pickandtest.portfolio.domain.service.ports.output.PortfolioStockRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PortfolioStockRepositoryImpl implements PortfolioStockRepository {

    private final PortfolioStockJpaRepository portfolioStockJpaRepository;
    private final PortfolioStockDataAccessMapper portfolioStockDataAccessMapper;
    private final PortfolioJpaRepository portfolioJpaRepository;

    @Override
    public PortfolioStock save(PortfolioStock portfolioStock) {
        PortfolioStockEntity portfolioStockEntity =
                portfolioStockDataAccessMapper.portfolioStockToPortfolioStockEntity(portfolioStock);
        PortfolioStockEntity saved = portfolioStockJpaRepository.save(portfolioStockEntity);
        return portfolioStockDataAccessMapper.portfolioStockEntityToPortfolioStock(saved);
    }

    @Override
    public void deleteAllByPortfolioId(Long portfolioId) {
        if (!portfolioJpaRepository.existsById(portfolioId)) {
            throw new RuntimeException("존재하지 않는 포트폴리오입니다");
        }
        portfolioStockJpaRepository.deleteAllByPortfolioEntityId(portfolioId);
    }

    @Override
    public List<PortfolioStock> saveAll(List<PortfolioStock> portfolioStocks) {
        List<PortfolioStockEntity> portfolioStockEntities = portfolioStocks.stream()
                .map(portfolioStockDataAccessMapper::portfolioStockToPortfolioStockEntity)
                .toList();
        List<PortfolioStockEntity> saved = portfolioStockJpaRepository.saveAll(portfolioStockEntities);
        return saved.stream()
                .map(portfolioStockDataAccessMapper::portfolioStockEntityToPortfolioStock)
                .toList();
    }
}
