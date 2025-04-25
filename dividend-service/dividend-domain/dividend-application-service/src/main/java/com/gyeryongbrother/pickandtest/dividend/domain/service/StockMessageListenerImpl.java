package com.gyeryongbrother.pickandtest.dividend.domain.service;

import com.gyeryongbrother.pickandtest.dividend.domain.core.entity.Stock;
import com.gyeryongbrother.pickandtest.dividend.domain.service.ports.input.DividendCollector;
import com.gyeryongbrother.pickandtest.dividend.domain.service.ports.input.message.listener.StockMessageListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class StockMessageListenerImpl implements StockMessageListener {

    private final DividendCollector dividendCollector;

    @Override
    public void stockCreated(Stock stock) {
        dividendCollector.collectDividends(stock.id());
    }
}
