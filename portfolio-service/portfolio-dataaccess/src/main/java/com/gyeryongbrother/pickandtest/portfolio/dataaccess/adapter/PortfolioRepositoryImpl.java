package com.gyeryongbrother.pickandtest.portfolio.dataaccess.adapter;

import com.gyeryongbrother.pickandtest.portfolio.dataaccess.entity.PortfolioEntity;
import com.gyeryongbrother.pickandtest.portfolio.dataaccess.mapper.PortfolioDataAccessMapper;
import com.gyeryongbrother.pickandtest.portfolio.dataaccess.repository.PortfolioJpaRepository;
import com.gyeryongbrother.pickandtest.portfolio.domain.core.entity.Portfolio;
import com.gyeryongbrother.pickandtest.portfolio.domain.service.ports.output.PortfolioRepository;
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
        portfolioEntity.syncPortfolioStockEntities();
        PortfolioEntity saved = portfolioJpaRepository.save(portfolioEntity);
        return portfolioDataAccessMapper.portfolioEntityToPortfolio(saved);
    }

    @Override
    public Portfolio update(Portfolio portfolio) {
        PortfolioEntity portfolioEntity = portfolioDataAccessMapper.portfolioToPortfolioEntity(portfolio);
        PortfolioEntity updatedEntity = portfolioJpaRepository.findById(portfolio.getId())
                .orElseThrow(()->new RuntimeException("존재하지 않는 포트폴리오입니다"));
        updatedEntity.setPortfolioStockEntities(portfolioEntity.getPortfolioStockEntities());
        updatedEntity.syncPortfolioStockEntities();
        return portfolioDataAccessMapper.portfolioEntityToPortfolio(updatedEntity);
    }
}
