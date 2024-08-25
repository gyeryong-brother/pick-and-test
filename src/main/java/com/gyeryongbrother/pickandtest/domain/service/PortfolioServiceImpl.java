package com.gyeryongbrother.pickandtest.domain.service;

import com.gyeryongbrother.pickandtest.application.dto.UpdatePortfolioStockRequests;
import com.gyeryongbrother.pickandtest.domain.core.PortfolioStock;
import com.gyeryongbrother.pickandtest.domain.service.dto.UpdatePortfolioStockCommand;
import com.gyeryongbrother.pickandtest.domain.service.ports.input.PortfolioService;
import com.gyeryongbrother.pickandtest.domain.service.ports.output.PortfolioStockRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PortfolioServiceImpl implements PortfolioService {

    private final PortfolioStockRepository portfolioStockRepository;

    @Override
    public List<PortfolioStock> updatePortfolioStocks(Long portfolioId,
                                                      UpdatePortfolioStockRequests updatePortfolioStockRequests) {
        List<UpdatePortfolioStockCommand> updatePortfolioStockCommands =
                updatePortfolioStockRequests.updatePortfolioStockRequests().stream()
                        .map(updatePortfolioStockRequest -> updatePortfolioStockRequest.toCommand(portfolioId))
                        .toList();
        List<PortfolioStock> portfolioStocks = updatePortfolioStockCommands.stream()
                .map(UpdatePortfolioStockCommand::toDomain)
                .toList();
        portfolioStockRepository.deleteAllByPortfolioId(portfolioId);
        return portfolioStockRepository.saveAll(portfolioStocks);
    }
}
