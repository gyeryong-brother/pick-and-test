package com.gyeryongbrother.pickandtest.portfolio.domain.service;

import com.gyeryongbrother.pickandtest.portfolio.domain.core.entity.Portfolio;
import com.gyeryongbrother.pickandtest.portfolio.domain.core.entity.PortfolioStock;
import com.gyeryongbrother.pickandtest.portfolio.domain.service.dto.DeletePortfolioCommand;
import com.gyeryongbrother.pickandtest.portfolio.domain.service.dto.PortfolioResponse;
import com.gyeryongbrother.pickandtest.portfolio.domain.service.dto.PortfoliosResponse;
import com.gyeryongbrother.pickandtest.portfolio.domain.service.dto.SavePortfolioCommand;
import com.gyeryongbrother.pickandtest.portfolio.domain.service.dto.UpdatePortfolioCommand;
import com.gyeryongbrother.pickandtest.portfolio.domain.service.dto.UpdatePortfolioResponse;
import com.gyeryongbrother.pickandtest.portfolio.domain.service.exception.PortfolioServiceException;
import com.gyeryongbrother.pickandtest.portfolio.domain.service.exception.PortfolioServiceExceptionType;
import com.gyeryongbrother.pickandtest.portfolio.domain.service.ports.input.PortfolioService;
import com.gyeryongbrother.pickandtest.portfolio.domain.service.ports.output.PortfolioQueryRepository;
import com.gyeryongbrother.pickandtest.portfolio.domain.service.ports.output.PortfolioRepository;
import com.gyeryongbrother.pickandtest.portfolio.domain.service.ports.output.PortfolioStockQueryRepository;
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
    public final PortfolioStockQueryRepository portfolioStockQueryRepository;

    @Override
    public UpdatePortfolioResponse updatePortfolio(UpdatePortfolioCommand updatePortfolioCommand) {
        Long portfolioId = updatePortfolioCommand.portfolioId();
        Long memberId = updatePortfolioCommand.memberId();
        Portfolio portfolio = portfolioQueryRepository.findById(portfolioId);
        if (portfolio.getMemberId() != memberId) {
            throw new PortfolioServiceException(PortfolioServiceExceptionType.INVALID_USER);
        }
        portfolioStockRepository.deleteAllByPortfolioId(portfolioId);
        Portfolio newPortfolio = updatePortfolioCommand.toDomain(portfolioId);
        portfolioStockRepository.saveAll(newPortfolio.getPortfolioStocks());
        List<PortfolioStock> portfolioStocks = portfolioStockQueryRepository.findAllByPortfolioId(portfolioId);
        Portfolio updated = new Portfolio(portfolioId, memberId, null, portfolioStocks);
        return UpdatePortfolioResponse.from(updated);
    }

    @Override
    public PortfoliosResponse deletePortfolio(DeletePortfolioCommand deletePortfolioCommand) {
        Long memberId = deletePortfolioCommand.memberId();
        Long portfolioId = deletePortfolioCommand.portfolioId();
        Portfolio portfolio = portfolioQueryRepository.findById(portfolioId);
        if (portfolio.getMemberId() != memberId) {
            throw new PortfolioServiceException(PortfolioServiceExceptionType.INVALID_USER);
        }
        portfolioRepository.delete(portfolioId);
        List<Portfolio> remained = portfolioQueryRepository.findAllByMemberId(memberId);
        List<PortfolioResponse> portfolioResponses = remained.stream()
                .map(PortfolioResponse::from)
                .toList();
        return new PortfoliosResponse(portfolioResponses);
    }

    @Override
    public UpdatePortfolioResponse savePortfolio(SavePortfolioCommand savePortfolioCommand) {
        Portfolio portfolio = savePortfolioCommand.toDomain();
        Portfolio saved = portfolioRepository.save(portfolio);
        return UpdatePortfolioResponse.from(saved);
    }

    public UpdatePortfolioResponse deductVirtualInvestment(Long portfolioId, Long memberId, Long amount) {
        Portfolio portfolio = portfolioQueryRepository.findByIdWithPessimisticLock(portfolioId);
        if (portfolio.getMemberId() != memberId) {
            throw new PortfolioServiceException(PortfolioServiceExceptionType.INVALID_USER);
        }
        Portfolio updatedPortfolio = portfolio.deductInvestment(amount);
        portfolioRepository.save(updatedPortfolio);
        return UpdatePortfolioResponse.from(updatedPortfolio);
    }
}
