package com.gyeryongbrother.pickandtest.domain.service;

import com.gyeryongbrother.pickandtest.domain.core.AnnualDividend;
import com.gyeryongbrother.pickandtest.domain.core.Dividends;
import com.gyeryongbrother.pickandtest.domain.service.dto.AnnualDividendResponse;
import com.gyeryongbrother.pickandtest.domain.service.ports.input.DividendQueryService;
import com.gyeryongbrother.pickandtest.domain.service.ports.output.DividendQueryRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class DividendQueryServiceImpl implements DividendQueryService {

    private final DividendQueryRepository dividendQueryRepository;

    @Override
    public List<AnnualDividendResponse> getAnnualDividendsById(Long id) {
        Dividends dividends = Dividends.from(dividendQueryRepository.findAllByStockId(id));
        List<AnnualDividend> annualDividends = dividends.getAnnualDividends();
        return annualDividends.stream()
                .map(AnnualDividendResponse::from)
                .toList();
    }
}
