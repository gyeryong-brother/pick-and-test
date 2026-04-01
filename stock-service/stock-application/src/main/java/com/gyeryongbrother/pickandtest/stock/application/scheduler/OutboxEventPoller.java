package com.gyeryongbrother.pickandtest.stock.application.scheduler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gyeryongbrother.pickandtest.stock.infrastructure.entity.OutboxEventEntity;
import com.gyeryongbrother.pickandtest.stock.infrastructure.repository.OutboxEventJpaRepository;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class OutboxEventPoller {

    private final OutboxEventJpaRepository outboxEventJpaRepository;
    private final KafkaTemplate<String, Object> objectKafkaTemplate;
    private final ObjectMapper objectMapper;

    public OutboxEventPoller(OutboxEventJpaRepository outboxEventJpaRepository,
                             KafkaTemplate<String, Object> objectKafkaTemplate,
                             ObjectMapper objectMapper) {
        this.outboxEventJpaRepository = outboxEventJpaRepository;
        this.objectKafkaTemplate = objectKafkaTemplate;
        this.objectMapper = objectMapper;
    }

    private static final String STOCK_CREATED_TOPIC = "stock-created-event";

    @Scheduled(fixedDelayString = "${outbox.poll.delay:5000}")
    @Transactional
    public void pollAndPublishStockCreatedEvents() {
        List<OutboxEventEntity> events = outboxEventJpaRepository.findUnpublishedEventsWithLock("STOCK_CREATED");
        for (OutboxEventEntity event : events) {
            try {
                Object payload = objectMapper.readValue(event.getPayload(), Object.class);
                objectKafkaTemplate.send(STOCK_CREATED_TOPIC, event.getAggregateId(), payload);
                outboxEventJpaRepository.delete(event);
                log.info("Published StockCreated event for stockId: {}", event.getAggregateId());
            } catch (Exception e) {
                log.error("Failed to publish StockCreated outbox event id: {}", event.getId(), e);
            }
        }
    }
}
