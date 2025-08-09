package com.gyeryongbrother.pickandtest.dividend.domain.service.ports.output;

import com.gyeryongbrother.pickandtest.dividend.domain.core.entity.Dividend;
import java.util.List;

public interface DividendRepository {

    Dividend save(Dividend dividend);

    void saveAll(List<Dividend> dividends);
}
