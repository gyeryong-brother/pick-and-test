package com.gyeryongbrother.pickandtest.dividend.domain.service.ports.input;

import com.gyeryongbrother.pickandtest.dividend.domain.core.entity.Stock;

public interface DividendCollector {

    void collectDividends(Stock stock);
}
