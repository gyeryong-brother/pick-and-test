package com.gyeryongbrother.pickandtest.portfolio.domain.service;

import com.gyeryongbrother.pickandtest.portfolio.domain.core.entity.Portfolio;
import com.gyeryongbrother.pickandtest.portfolio.domain.service.dto.UpdatePortfolioCommand;
import com.gyeryongbrother.pickandtest.portfolio.domain.service.dto.UpdatePortfolioResponse;
import com.gyeryongbrother.pickandtest.portfolio.domain.service.ports.input.PortfolioService;
import com.gyeryongbrother.pickandtest.portfolio.domain.service.ports.output.PortfolioRepository;
import com.gyeryongbrother.pickandtest.portfolio.domain.service.ports.output.PortfolioStockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PortfolioServiceImpl implements PortfolioService {

    public final PortfolioRepository portfolioRepository;
    public final PortfolioStockRepository portfolioStockRepository;

    @Override
    public UpdatePortfolioResponse updatePortfolio(UpdatePortfolioCommand updatePortfolioCommand) {
        Portfolio portfolio = updatePortfolioCommand.toDomain(updatePortfolioCommand.portfolioId());
        portfolioStockRepository.deleteAllByPortfolioId(portfolio.getId());
        Portfolio updated = portfolioRepository.update(portfolio);
        portfolioStockRepository.saveAll(portfolio.getPortfolioStocks());
        return UpdatePortfolioResponse.from(updated);
    }
}
