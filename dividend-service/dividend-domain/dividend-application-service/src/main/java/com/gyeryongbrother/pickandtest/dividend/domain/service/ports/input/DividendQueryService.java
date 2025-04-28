package com.gyeryongbrother.pickandtest.dividend.domain.service.ports.input;

import com.gyeryongbrother.pickandtest.dividend.domain.service.dto.AnnualDividendResponse;
import java.util.List;

public interface DividendQueryService {

    List<AnnualDividendResponse> findAnnualDividendsByStockId(Long stockId);
}
