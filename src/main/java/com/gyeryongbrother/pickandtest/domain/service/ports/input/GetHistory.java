package com.gyeryongbrother.pickandtest.domain.service.ports.input;

import com.gyeryongbrother.pickandtest.infrastructure.client.koreainvestment.stock.dto.AnnualDividend;
import java.util.List;

public interface GetHistory {

    List<AnnualDividend> getAnnualDividendHistoryByName(String name);

    //Map<Integer, BigDecimal> getAnnualDividendHistoryBySymbol(String symbol);
}
