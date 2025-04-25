package com.gyeryongbrother.pickandtest.dividend.domain.service.ports.output;

import com.gyeryongbrother.pickandtest.dividend.domain.core.entity.Dividend;
import java.time.LocalDate;
import java.util.List;

public interface DividendFetcher {

    List<Dividend> fetchDividends(Long stockId, LocalDate startDate);
}
