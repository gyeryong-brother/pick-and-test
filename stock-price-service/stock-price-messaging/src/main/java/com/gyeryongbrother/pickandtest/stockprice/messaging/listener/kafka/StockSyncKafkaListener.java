package com.gyeryongbrother.pickandtest.stockprice.messaging.listener.kafka;

import com.gyeryongbrother.pickandtest.stockprice.dataaccess.entity.LocalStockEntity;
import com.gyeryongbrother.pickandtest.stockprice.dataaccess.repository.LocalStockJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Component
@RequiredArgsConstructor
@Slf4j
public class StockSyncKafkaListener {

    private final LocalStockJpaRepository localStockJpaRepository;

    @RetryableTopic(
            attempts = "3",
            backoff = @Backoff(delay = 1000, multiplier = 2.0),
            autoCreateTopics = "true",
            dltTopicSuffix = ".DLT"
    )
    @KafkaListener(
            groupId = "stock-price-stock-sync",
            topics = "stock-created-event",
            containerFactory = "stockPriceCollectionRequestedEventKafkaListenerContainerFactory"
    )
    @Transactional
    public void receiveStockCreatedEvent(Map<String, Object> payload) {
        try {
            Long stockId = ((Number) payload.get("stockId")).longValue();
            String symbol = (String) payload.get("symbol");

            if (!localStockJpaRepository.existsById(stockId)) {
                localStockJpaRepository.save(new LocalStockEntity(stockId, symbol));
                log.info("New stock synced to local cache: {} (id: {})", symbol, stockId);
            }
        } catch (Exception e) {
            log.error("Failed to sync stock from event: {}", payload, e);
            throw e;
        }
    }

    @org.springframework.kafka.annotation.DltHandler
    public void handleDlt(Map<String, Object> payload,
            @org.springframework.messaging.handler.annotation.Header(
                org.springframework.kafka.support.KafkaHeaders.RECEIVED_TOPIC) String topic) {
        log.error("[DLT] StockCreated 이벤트가 최종 실패하여 DLT로 이동: payload={}, topic={}", payload, topic);
    }
}
