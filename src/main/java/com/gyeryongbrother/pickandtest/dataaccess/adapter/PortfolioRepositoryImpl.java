package com.gyeryongbrother.pickandtest.dataaccess.adapter;

import com.gyeryongbrother.pickandtest.dataaccess.entity.PortfolioEntity;
import com.gyeryongbrother.pickandtest.dataaccess.mapper.PortfolioDataAccessMapper;
import com.gyeryongbrother.pickandtest.dataaccess.repository.PortfolioJpaRepository;
import com.gyeryongbrother.pickandtest.domain.core.Portfolio;
import com.gyeryongbrother.pickandtest.domain.service.ports.output.PortfolioRepository;
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
        return portfolioDataAccessMapper.portfolioEntityToPortfolio(saved);
    }
}
