package com.gyeryongbrother.pickandtest.domain.service;

import com.gyeryongbrother.pickandtest.domain.core.PortfolioStock;
import com.gyeryongbrother.pickandtest.domain.service.dto.PortfolioStockResponse;
import com.gyeryongbrother.pickandtest.domain.service.ports.input.PortfolioStockQueryService;
import com.gyeryongbrother.pickandtest.domain.service.ports.output.PortfolioStockQueryRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class PortfolioStockQueryServiceImpl implements PortfolioStockQueryService {

    private final PortfolioStockQueryRepository portfolioStockQueryRepository;

    @Override
    public List<PortfolioStockResponse> findAllByPortfolioId(Long portfolioId) {
        List<PortfolioStock> portfolioStocks = portfolioStockQueryRepository.findAllByPortfolioId(portfolioId);
        return portfolioStocks.stream().map(PortfolioStockResponse::from).toList();
    }
}
