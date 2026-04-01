package com.gyeryongbrother.pickandtest.stockprice.messaging;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;

import com.gyeryongbrother.pickandtest.stockprice.dataaccess.entity.LocalStockEntity;
import com.gyeryongbrother.pickandtest.stockprice.dataaccess.repository.LocalStockJpaRepository;
import java.time.Duration;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(properties = {
    "spring.cloud.config.enabled=false",
    "eureka.client.enabled=false",
    "spring.config.import=optional:configserver:",
    "spring.batch.job.enabled=false",
    "spring.kafka.bootstrap-servers=${spring.embedded.kafka.brokers}",
    "kafka.bootstrap-servers=${spring.embedded.kafka.brokers}",
    "spring.kafka.listener.auto-startup=true"
})
@EmbeddedKafka(
    partitions = 1,
    topics = {
        "stock-created-event",
        "stock-created-event-retry-0",
        "stock-created-event-retry-1",
        "stock-created-event.DLT"
    }
)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@ActiveProfiles("test")
class StockSyncKafkaListenerIntegrationTest {

    @Autowired
    private KafkaTemplate<String, Object> stringObjectKafkaTemplate;

    @Autowired
    private LocalStockJpaRepository localStockJpaRepository;

    @BeforeEach
    void setUp() {
        localStockJpaRepository.deleteAll();
    }

    @Test
    @DisplayName("stock-created-event 수신 시 local_stocks 캐시에 종목이 저장된다")
    void givenStockCreatedEvent_whenReceived_thenStockSyncedToLocalCache() {
        // given
        Map<String, Object> stockCreatedPayload = Map.of(
            "stockId", 1L,
            "symbol", "AAPL"
        );

        // when
        stringObjectKafkaTemplate.send("stock-created-event", "1", stockCreatedPayload);

        // then
        await().atMost(Duration.ofSeconds(5)).untilAsserted(() -> {
            assertThat(localStockJpaRepository.existsById(1L)).isTrue();
            LocalStockEntity cached = localStockJpaRepository.findById(1L).orElseThrow();
            assertThat(cached.getSymbol()).isEqualTo("AAPL");
        });
    }

    @Test
    @DisplayName("같은 stockId로 이벤트가 두 번 발행되어도 local_stocks에 중복 저장되지 않는다")
    void givenDuplicateStockCreatedEvent_whenReceived_thenNoduplicateInLocalCache() {
        // given
        Map<String, Object> payload = Map.of("stockId", 2L, "symbol", "MSFT");

        // when
        stringObjectKafkaTemplate.send("stock-created-event", "2", payload);
        stringObjectKafkaTemplate.send("stock-created-event", "2", payload);

        // then
        await().atMost(Duration.ofSeconds(5)).untilAsserted(() -> {
            long count = localStockJpaRepository.findAll().stream()
                    .filter(s -> s.getStockId().equals(2L))
                    .count();
            assertThat(count).isEqualTo(1);
        });
    }

    @Test
    @DisplayName("여러 종목의 StockCreated 이벤트가 모두 캐시에 동기화된다")
    void givenMultipleStockCreatedEvents_whenReceived_thenAllSyncedToLocalCache() {
        // given
        stringObjectKafkaTemplate.send("stock-created-event", "10", Map.of("stockId", 10L, "symbol", "AMZN"));
        stringObjectKafkaTemplate.send("stock-created-event", "11", Map.of("stockId", 11L, "symbol", "GOOGL"));
        stringObjectKafkaTemplate.send("stock-created-event", "12", Map.of("stockId", 12L, "symbol", "TSLA"));

        // then
        await().atMost(Duration.ofSeconds(5)).untilAsserted(() ->
            assertThat(localStockJpaRepository.count()).isGreaterThanOrEqualTo(3)
        );
    }
}
