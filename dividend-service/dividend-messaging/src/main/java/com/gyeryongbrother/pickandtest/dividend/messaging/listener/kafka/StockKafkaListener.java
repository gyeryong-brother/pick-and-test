package com.gyeryongbrother.pickandtest.dividend.messaging.listener.kafka;

import com.gyeryongbrother.pickandtest.dividend.domain.service.ports.input.StockMessageListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class StockKafkaListener {

    private final StockMessageListener stockMessageListener;
}
