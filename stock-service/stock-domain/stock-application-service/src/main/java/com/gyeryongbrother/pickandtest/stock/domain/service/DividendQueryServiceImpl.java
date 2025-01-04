package com.gyeryongbrother.pickandtest.stock.domain.service;

import com.gyeryongbrother.pickandtest.stock.domain.service.dto.AnnualDividendResponse;
import com.gyeryongbrother.pickandtest.stock.domain.core.entity.Dividends;
import com.gyeryongbrother.pickandtest.stock.domain.service.ports.input.DividendQueryService;
import com.gyeryongbrother.pickandtest.stock.domain.service.ports.output.DividendQueryRepository;
import com.gyeryongbrother.pickandtest.stock.domain.core.valueobject.AnnualDividend;
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
