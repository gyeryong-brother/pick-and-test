package com.gyeryongbrother.pickandtest.portfolio.domain.service;

import com.gyeryongbrother.pickandtest.portfolio.domain.core.entity.Portfolio;
import com.gyeryongbrother.pickandtest.portfolio.domain.service.dto.DeletePortfolioCommand;
import com.gyeryongbrother.pickandtest.portfolio.domain.service.dto.PortfolioResponse;
import com.gyeryongbrother.pickandtest.portfolio.domain.service.dto.PortfoliosResponse;
import com.gyeryongbrother.pickandtest.portfolio.domain.service.dto.SavePortfolioCommand;
import com.gyeryongbrother.pickandtest.portfolio.domain.service.dto.UpdatePortfolioCommand;
import com.gyeryongbrother.pickandtest.portfolio.domain.service.dto.UpdatePortfolioResponse;
import com.gyeryongbrother.pickandtest.portfolio.domain.service.ports.input.PortfolioService;
import com.gyeryongbrother.pickandtest.portfolio.domain.service.ports.output.PortfolioQueryRepository;
import com.gyeryongbrother.pickandtest.portfolio.domain.service.ports.output.PortfolioRepository;
import com.gyeryongbrother.pickandtest.portfolio.domain.service.ports.output.PortfolioStockRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PortfolioServiceImpl implements PortfolioService {

    public final PortfolioRepository portfolioRepository;
    public final PortfolioStockRepository portfolioStockRepository;
    public final PortfolioQueryRepository portfolioQueryRepository;

    @Override
    public UpdatePortfolioResponse updatePortfolio(UpdatePortfolioCommand updatePortfolioCommand) {
        Portfolio portfolio = updatePortfolioCommand.toDomain(updatePortfolioCommand.portfolioId());
        portfolioStockRepository.deleteAllByPortfolioId(portfolio.getId());
        portfolioStockRepository.saveAll(portfolio.getPortfolioStocks());
        Portfolio updated = portfolioQueryRepository.findById(portfolio.getId());
        return UpdatePortfolioResponse.from(updated);
    }

    @Override
    public PortfoliosResponse deletePortfolio(DeletePortfolioCommand deletePortfolioCommand) {
        Long memberId= deletePortfolioCommand.memberId();
        Long portfolioId =deletePortfolioCommand.portfolioId();
        portfolioRepository.delete(portfolioId);
        List<Portfolio> remained=portfolioQueryRepository.findAllByMemberId(memberId);
        List<PortfolioResponse> portfolioResponses=remained.stream()
                .map(PortfolioResponse::from)
                .toList();
        return new PortfoliosResponse(portfolioResponses);
    }

    @Override
    public UpdatePortfolioResponse savePortfolio(SavePortfolioCommand savePortfolioCommand) {
        Portfolio portfolio=savePortfolioCommand.toDomain();
        Portfolio saved=portfolioRepository.save(portfolio);
        return UpdatePortfolioResponse.from(saved);
    }
}
