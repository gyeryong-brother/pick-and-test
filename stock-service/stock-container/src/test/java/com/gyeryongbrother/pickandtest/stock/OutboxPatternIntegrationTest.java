package com.gyeryongbrother.pickandtest.stock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;

import com.gyeryongbrother.pickandtest.stock.infrastructure.entity.OutboxEventEntity;
import com.gyeryongbrother.pickandtest.stock.infrastructure.repository.OutboxEventJpaRepository;
import com.gyeryongbrother.pickandtest.stock.dataaccess.repository.StockJpaRepository;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest(properties = {
    "spring.cloud.config.enabled=false",
    "eureka.client.enabled=false",
    "spring.config.import=optional:configserver:",
    "outbox.poll.delay=500",
    "spring.kafka.listener.auto-startup=true"
})
@org.springframework.scheduling.annotation.EnableScheduling
@EmbeddedKafka(
    partitions = 1,
    topics = {
        "stock-created-event",
        "stock-price-collection-requested-event",
        "stock-minute-price-collection-requested-event"
    }
)
@TestPropertySource(properties = {
    "spring.kafka.bootstrap-servers=${spring.embedded.kafka.brokers}",
    "kafka.bootstrap-servers=${spring.embedded.kafka.brokers}"
})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@ActiveProfiles("test")
public class OutboxPatternIntegrationTest {

    @Autowired
    private OutboxEventJpaRepository outboxEventJpaRepository;

    @Autowired
    private StockJpaRepository stockJpaRepository;

    @Autowired(required = false)
    private com.gyeryongbrother.pickandtest.stock.application.scheduler.OutboxEventPoller outboxEventPoller;

    @BeforeEach
    @Transactional
    void setUp() {
        outboxEventJpaRepository.deleteAll();
        TestKafkaReceiver.receivedCount.set(0);
    }

    @Test
    @DisplayName("스케줄러 실행 시 Kafka에 직접 발행하지 않고 outbox_events 테이블에 원자적으로 저장된다")
    void givenStocksExist_whenSchedulerRuns_thenEventsStoredInOutboxAtomically() {
        // when: 스케줄러 실행 (트랜잭셔널하게 outbox에 저장)

        // then: DB에 등록된 주식 수만큼 Outbox 레코드가 저장되어야 함
        long stockCount = stockJpaRepository.count();
        List<OutboxEventEntity> outboxEvents = outboxEventJpaRepository.findAll();

        assertThat(outboxEvents).hasSize((int) stockCount);
        assertThat(outboxEvents).allMatch(e -> "STOCK_CREATED".equals(e.getAggregateType()));
    }

    @Test
    @DisplayName("OutboxEventPoller가 outbox를 읽어 Kafka로 발행하고, 성공 시 outbox_events 테이블에서 삭제한다")
    void givenOutboxHasEvents_whenPollerRuns_thenPublishedToKafkaAndDeletedFromOutbox() {
        // given
        long stockCount = stockJpaRepository.count();
        assertThat(outboxEventJpaRepository.count()).isEqualTo(stockCount);

        // when & then
        await().atMost(Duration.ofSeconds(5)).untilAsserted(() -> {
            assertThat(TestKafkaReceiver.receivedCount.get()).isEqualTo((int) stockCount);
            assertThat(outboxEventJpaRepository.count()).isEqualTo(0);
        });
    }

    @Component
    static class TestKafkaReceiver {
        static final AtomicInteger receivedCount = new AtomicInteger(0);

        @KafkaListener(groupId = "outbox-test-group", topics = "stock-created-event",
            properties = {"auto.offset.reset=earliest"})
        public void receive(@Payload Object event) {
            System.out.println("Received Kafka event: " + event);
            receivedCount.incrementAndGet();
        }
    }
}
