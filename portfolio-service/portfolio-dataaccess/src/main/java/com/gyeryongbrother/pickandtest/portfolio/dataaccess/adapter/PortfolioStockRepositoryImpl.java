package com.gyeryongbrother.pickandtest.portfolio.dataaccess.adapter;

import com.gyeryongbrother.pickandtest.portfolio.dataaccess.entity.PortfolioEntity;
import com.gyeryongbrother.pickandtest.portfolio.dataaccess.entity.PortfolioStockEntity;
import com.gyeryongbrother.pickandtest.portfolio.dataaccess.mapper.PortfolioStockDataAccessMapper;
import com.gyeryongbrother.pickandtest.portfolio.dataaccess.repository.PortfolioJpaRepository;
import com.gyeryongbrother.pickandtest.portfolio.dataaccess.repository.PortfolioStockJpaRepository;
import com.gyeryongbrother.pickandtest.portfolio.domain.core.entity.PortfolioStock;
import com.gyeryongbrother.pickandtest.portfolio.domain.service.ports.output.PortfolioStockRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
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
    @Modifying(clearAutomatically = true)
    public void deleteAllByPortfolioId(Long portfolioId) {
        validatePortfolioExist(portfolioId);
        portfolioStockJpaRepository.deleteAllByPortfolioEntityId(portfolioId);
    }

    private void validatePortfolioExist(Long portfolioId) {
        Optional<PortfolioEntity> portfolio = portfolioJpaRepository.findById(portfolioId);
        if (portfolio.isPresent()) {
            return;
        }
        throw new RuntimeException("값이 없습니다.");
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
