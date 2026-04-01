package com.gyeryongbrother.pickandtest.stock.infrastructure.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gyeryongbrother.pickandtest.stock.domain.core.entity.Stock;
import com.gyeryongbrother.pickandtest.stock.domain.service.ports.output.OutboxRepository;
import com.gyeryongbrother.pickandtest.stock.infrastructure.entity.OutboxEventEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class OutboxRepositoryImpl implements OutboxRepository {

    private final OutboxEventJpaRepository outboxEventJpaRepository;
    private final ObjectMapper objectMapper;

    @Override
    public void saveStockCreated(Stock stock) {
        record StockCreatedPayload(Long stockId, String symbol) {}
        StockCreatedPayload payload = new StockCreatedPayload(stock.id(), stock.symbol());
        String jsonPayload = serialize(payload);
        OutboxEventEntity entity = new OutboxEventEntity(
                "STOCK_CREATED",
                stock.id().toString(),
                jsonPayload
        );
        outboxEventJpaRepository.save(entity);
        log.info("Saved StockCreated outbox event for stock: {}", stock.symbol());
    }

    private String serialize(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error("Failed to serialize outbox event payload", e);
            throw new RuntimeException("Serialization error", e);
        }
    }
}
