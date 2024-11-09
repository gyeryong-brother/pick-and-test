package com.gyeryongbrother.pickandtest.domain.service;

import com.gyeryongbrother.pickandtest.domain.core.Portfolio;
import com.gyeryongbrother.pickandtest.domain.core.PortfolioStock;
import com.gyeryongbrother.pickandtest.domain.service.dto.PortfolioResponse;
import com.gyeryongbrother.pickandtest.domain.service.dto.PortfolioStockResponse;
import com.gyeryongbrother.pickandtest.domain.service.ports.input.PortfolioQueryService;
import com.gyeryongbrother.pickandtest.domain.service.ports.output.PortfolioQueryRepository;
import com.gyeryongbrother.pickandtest.domain.service.ports.output.PortfolioStockQueryRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional
public class PortfolioQueryServiceImpl implements PortfolioQueryService {

    private final PortfolioStockQueryRepository portfolioStockQueryRepository;
    private final PortfolioQueryRepository portfolioQueryRepository;

    @Override
    public List<PortfolioStockResponse> findAllByPortfolioId(Long portfolioId) {
        List<PortfolioStock> portfolioStocks = portfolioStockQueryRepository.findAllByPortfolioId(portfolioId);
        return portfolioStocks.stream()
                .map(PortfolioStockResponse::from)
                .toList();
    }

    @Override
    public List<PortfolioResponse> findAllPortfolios() {
        List<Portfolio> portfolios = portfolioQueryRepository.findAllByMemberId(1L);
        return portfolios.stream()
                .map(PortfolioResponse::from)
                .toList();
    }
}
