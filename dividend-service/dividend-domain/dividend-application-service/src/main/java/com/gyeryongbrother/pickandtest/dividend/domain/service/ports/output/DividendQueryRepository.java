package com.gyeryongbrother.pickandtest.dividend.domain.service.ports.output;

import com.gyeryongbrother.pickandtest.dividend.domain.core.valueobject.Dividends;
import java.time.LocalDate;

public interface DividendQueryRepository {

    Dividends findDividendsByStockId(Long stockId);

    LocalDate findLastDateOfDividendsByStockId(Long stockId);
}
