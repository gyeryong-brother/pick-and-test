package com.gyeryongbrother.pickandtest.stock.messaging.publisher.kafka.config;

import com.gyeryongbrother.pickandtest.stock.messaging.publisher.kafka.dto.StockCreatedEvent;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

@Configuration
@Slf4j
public class KafkaConfig {

    @Bean
    public ProducerFactory<String, StockCreatedEvent> producerFactory() {
        Map<String, Object> props = new HashMap<>();
        log.info("=========producer config initialize=========");
        log.info("=========producer config initialize=========");
        log.info("=========producer config initialize=========");
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "3.38.169.241:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(props);
    }

    @Bean
    public KafkaTemplate<String, StockCreatedEvent> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}
