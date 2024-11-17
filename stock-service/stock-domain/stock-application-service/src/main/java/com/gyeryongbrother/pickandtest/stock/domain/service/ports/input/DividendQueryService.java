package com.gyeryongbrother.pickandtest.stock.domain.service.ports.input;

import com.gyeryongbrother.pickandtest.stock.domain.service.dto.AnnualDividendResponse;
import java.util.List;

public interface DividendQueryService {

    List<AnnualDividendResponse> getAnnualDividendsById(Long id);
}
