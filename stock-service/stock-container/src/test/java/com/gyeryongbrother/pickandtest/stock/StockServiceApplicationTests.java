package com.gyeryongbrother.pickandtest.stock;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootTest
@DisplayName("어플리케이션을 실행한다")
class StockServiceApplicationTests {

    @MockBean
    private KafkaTemplate<String, Object> objectKafkaTemplate;

    @MockBean
    private KafkaAdmin kafkaAdmin;

    @Test
    @DisplayName("어플리케이션을 실행한다")
    void contextLoads() {
    }
}
