package com.gyeryongbrother.pickandtest.stock.messaging.publisher.kafka.config;

import com.gyeryongbrother.pickandtest.stock.messaging.publisher.kafka.dto.StockMinutePriceCollectionRequestedEvent;
import com.gyeryongbrother.pickandtest.stock.messaging.publisher.kafka.dto.StockPriceCollectionRequestedEvent;
import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

@Configuration
public class KafkaConfig {

    private final String bootstrapServers;

    public KafkaConfig(
            @Value("${kafka.bootstrap-servers}") String bootstrapServers
    ) {
        this.bootstrapServers = bootstrapServers;
    }

    @Bean
    public ProducerFactory<String, StockPriceCollectionRequestedEvent> stockPriceCollectionRequestedEventProducerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    private Map<String, Object> producerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return props;
    }

    @Bean
    public KafkaTemplate<String, StockPriceCollectionRequestedEvent> stockPriceCollectionRequestedEventKafkaTemplate() {
        return new KafkaTemplate<>(stockPriceCollectionRequestedEventProducerFactory());
    }

    @Bean
    public ProducerFactory<String, StockMinutePriceCollectionRequestedEvent> stockMinutePriceCollectionRequestedEventProducerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean
    public KafkaTemplate<String, StockMinutePriceCollectionRequestedEvent> stockMinutePriceCollectionRequestedEventKafkaTemplate() {
        return new KafkaTemplate<>(stockMinutePriceCollectionRequestedEventProducerFactory());
    }
}
