package com.gyeryongbrother.pickandtest.dividend.domain.service.ports.output;

import com.gyeryongbrother.pickandtest.dividend.domain.core.entity.Dividend;
import com.gyeryongbrother.pickandtest.dividend.domain.core.entity.Stock;
import java.time.LocalDate;
import java.util.List;

public interface DividendFetcher {

    List<Dividend> fetchDividends(Stock stock, LocalDate startDate);
}
