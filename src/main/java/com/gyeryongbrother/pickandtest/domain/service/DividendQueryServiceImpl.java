package com.gyeryongbrother.pickandtest.domain.service;

import com.gyeryongbrother.pickandtest.domain.core.Dividend;
import com.gyeryongbrother.pickandtest.domain.core.Stock;
import com.gyeryongbrother.pickandtest.domain.service.ports.input.DividendQueryService;
import com.gyeryongbrother.pickandtest.domain.service.ports.output.StockQueryRepository;
import com.gyeryongbrother.pickandtest.domain.service.dto.AnnualDividendResponse;
import com.gyeryongbrother.pickandtest.infrastructure.mapper.AnnualDividendMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class DividendQueryServiceImpl implements DividendQueryService {

    public final StockQueryRepository stockQueryRepository;
    public final AnnualDividendMapper annualDividendMapper;

    @Override
    public List<AnnualDividendResponse> getAnnualDividendsById(Long id) {
        Stock stock = stockQueryRepository.findById(id);
        List<Dividend> dividends = stock.getDividends();
        return annualDividendMapper.DividendsToAnnualDividends(dividends);
    }
}
