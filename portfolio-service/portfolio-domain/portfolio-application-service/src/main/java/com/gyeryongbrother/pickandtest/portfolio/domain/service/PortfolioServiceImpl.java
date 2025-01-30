package com.gyeryongbrother.pickandtest.portfolio.domain.service;

import com.gyeryongbrother.pickandtest.portfolio.domain.core.entity.Portfolio;
import com.gyeryongbrother.pickandtest.portfolio.domain.service.dto.UpdatePortfolioCommand;
import com.gyeryongbrother.pickandtest.portfolio.domain.service.dto.UpdatePortfolioResponse;
import com.gyeryongbrother.pickandtest.portfolio.domain.service.ports.input.PortfolioService;
import com.gyeryongbrother.pickandtest.portfolio.domain.service.ports.output.PortfolioQueryRepository;
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
    public final PortfolioQueryRepository portfolioQueryRepository;

    @Override
    public UpdatePortfolioResponse updatePortfolio(UpdatePortfolioCommand updatePortfolioCommand) {
        Portfolio portfolio = updatePortfolioCommand.toDomain(updatePortfolioCommand.portfolioId());
        portfolioStockRepository.deleteAllByPortfolioId(portfolio.getId());
        portfolioStockRepository.saveAll(portfolio.getPortfolioStocks());
        Portfolio updated = portfolioQueryRepository.findById(portfolio.getId());
        return UpdatePortfolioResponse.from(updated);
    }
}
// update 나 delete 시에 api 응답을 주느냐라는 문제랑도 관련이 있는데
// 사실 성공적으로 반영된 애를 보고 싶으면 조회하는게 좋을수 있고 -> 어차피 delete save 에서 문제가 생겼다면 db 단에서 예외를 던져서 막히는 부분에서 실행을 멈추고

// transactional 이 걸려있으니까 롤백이 되겠지? -> 그러니까 사실 상관 없다 마인드가 있기도해
