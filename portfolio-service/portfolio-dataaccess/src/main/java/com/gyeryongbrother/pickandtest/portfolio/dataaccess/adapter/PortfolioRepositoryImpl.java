package com.gyeryongbrother.pickandtest.portfolio.dataaccess.adapter;

import com.gyeryongbrother.pickandtest.portfolio.dataaccess.entity.PortfolioEntity;
import com.gyeryongbrother.pickandtest.portfolio.dataaccess.entity.PortfolioStockEntity;
import com.gyeryongbrother.pickandtest.portfolio.dataaccess.mapper.PortfolioDataAccessMapper;
import com.gyeryongbrother.pickandtest.portfolio.dataaccess.repository.PortfolioJpaRepository;
import com.gyeryongbrother.pickandtest.portfolio.domain.core.entity.Portfolio;
import com.gyeryongbrother.pickandtest.portfolio.domain.service.ports.output.PortfolioRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PortfolioRepositoryImpl implements PortfolioRepository {

    private final PortfolioDataAccessMapper portfolioDataAccessMapper;
    private final PortfolioJpaRepository portfolioJpaRepository;


    @Override
    public Portfolio save(Portfolio portfolio) {
        PortfolioEntity portfolioEntity = portfolioDataAccessMapper.portfolioToPortfolioEntity(portfolio);
        PortfolioEntity saved = portfolioJpaRepository.save(portfolioEntity);
        saved.setPortfolioStockEntities();
        return portfolioDataAccessMapper.portfolioEntityToPortfolio(saved);
    }

    @Override
    public void deleteById(Long Id) {
        portfolioJpaRepository.deleteById(Id);
    }
}
