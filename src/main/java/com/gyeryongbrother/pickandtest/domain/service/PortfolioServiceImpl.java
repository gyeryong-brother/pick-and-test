package com.gyeryongbrother.pickandtest.domain.service;

import com.gyeryongbrother.pickandtest.domain.core.PortfolioStock;
import com.gyeryongbrother.pickandtest.domain.service.dto.UpdatePortfolioCommand;
import com.gyeryongbrother.pickandtest.domain.service.dto.UpdatePortfolioStockCommand;
import com.gyeryongbrother.pickandtest.domain.service.ports.input.PortfolioService;
import com.gyeryongbrother.pickandtest.domain.service.ports.output.PortfolioStockRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PortfolioServiceImpl implements PortfolioService {

    private final PortfolioStockRepository portfolioStockRepository;

    @Override
    public List<PortfolioStock> updatePortfolioStocks(UpdatePortfolioCommand updatePortfolioCommand) {
        List<UpdatePortfolioStockCommand> updatePortfolioStockCommands =
                updatePortfolioCommand.updatePortfolioStockRequests().updatePortfolioStockRequests().stream()
                        .map(updatePortfolioStockRequest -> updatePortfolioStockRequest.toCommand(
                                updatePortfolioCommand.portfolioId()))
                        .toList();
        List<PortfolioStock> portfolioStocks = updatePortfolioStockCommands.stream()
                .map(UpdatePortfolioStockCommand::toDomain)
                .toList();
        portfolioStockRepository.deleteAllByPortfolioId(updatePortfolioCommand.portfolioId());
        return portfolioStockRepository.saveAll(portfolioStocks);
    }
}
