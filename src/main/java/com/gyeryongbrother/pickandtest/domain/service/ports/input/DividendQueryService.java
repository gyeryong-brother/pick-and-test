package com.gyeryongbrother.pickandtest.domain.service.ports.input;

import com.gyeryongbrother.pickandtest.domain.service.dto.AnnualDividendResponse;
import java.util.List;

public interface DividendQueryService {

    List<AnnualDividendResponse> getAnnualDividendsById(Long id);
}
