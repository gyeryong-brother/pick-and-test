package com.gyeryongbrother.pickandtest.dividend.domain.service.ports.input.message.listener;

import com.gyeryongbrother.pickandtest.dividend.domain.core.entity.Stock;

public interface StockMessageListener {

    void stockCreated(Stock stock);
}
