package com.gyeryongbrother.pickandtest.dividend.domain.service;

import com.gyeryongbrother.pickandtest.dividend.domain.core.valueobject.AnnualDividend;
import com.gyeryongbrother.pickandtest.dividend.domain.core.valueobject.Dividends;
import com.gyeryongbrother.pickandtest.dividend.domain.service.dto.AnnualDividendResponse;
import com.gyeryongbrother.pickandtest.dividend.domain.service.ports.input.DividendQueryService;
import com.gyeryongbrother.pickandtest.dividend.domain.service.ports.output.DividendQueryRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DividendQueryServiceImpl implements DividendQueryService {

    private final DividendQueryRepository dividendQueryRepository;

    @Override
    public List<AnnualDividendResponse> findAnnualDividendsByStockId(Long stockId) {
        Dividends dividends = dividendQueryRepository.findDividendsByStockId(stockId);
        List<AnnualDividend> annualDividends = dividends.getAnnualDividends();
        return annualDividends.stream()
                .map(AnnualDividendResponse::from)
                .toList();
    }
}
