package com.gyeryongbrother.pickandtest.dividend.infrastructure.adapter;

import com.gyeryongbrother.pickandtest.dividend.domain.core.entity.Dividend;
import com.gyeryongbrother.pickandtest.dividend.domain.service.ports.output.DividendFetcher;
import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class DividendFetcherImpl implements DividendFetcher {

    @Override
    public List<Dividend> fetchDividends(Long stockId, LocalDate startDate) {
        return null;
    }
}
