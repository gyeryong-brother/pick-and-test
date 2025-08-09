package com.gyeryongbrother.pickandtest.stockprice.messaging.listener.kafka.config;

import com.gyeryongbrother.pickandtest.stockprice.messaging.listener.kafka.dto.StockMinutePriceCollectionRequestedEvent;
import com.gyeryongbrother.pickandtest.stockprice.messaging.listener.kafka.dto.StockPriceCollectionRequestedEvent;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

@Configuration
@Slf4j
public class KafkaConfig {

    private final String bootstrapServers;

    public KafkaConfig(
            @Value("${kafka.bootstrap-servers}") String bootstrapServers
    ) {
        this.bootstrapServers = bootstrapServers;
    }

    @Bean
    public ConsumerFactory<String, StockPriceCollectionRequestedEvent> stockPriceCollectionRequestedEventConsumerFactory() {
        JsonDeserializer<StockPriceCollectionRequestedEvent> deserializer =
                new JsonDeserializer<>(StockPriceCollectionRequestedEvent.class, false);
        return new DefaultKafkaConsumerFactory<>(consumerConfigs(), new StringDeserializer(), deserializer);
    }

    private Map<String, Object> consumerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "stock-price-listener");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 10);
        return props;
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, StockPriceCollectionRequestedEvent> stockPriceCollectionRequestedEventKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, StockPriceCollectionRequestedEvent> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(stockPriceCollectionRequestedEventConsumerFactory());
        factory.setBatchListener(true);
        factory.setConcurrency(3);
        return factory;
    }

    @Bean
    public ConsumerFactory<String, StockMinutePriceCollectionRequestedEvent> stockMinutePriceCollectionRequestedEventConsumerFactory() {
        JsonDeserializer<StockMinutePriceCollectionRequestedEvent> deserializer =
                new JsonDeserializer<>(StockMinutePriceCollectionRequestedEvent.class, false);
        return new DefaultKafkaConsumerFactory<>(consumerConfigs(), new StringDeserializer(), deserializer);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, StockMinutePriceCollectionRequestedEvent> stockMinutePriceCollectionRequestedEventKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, StockMinutePriceCollectionRequestedEvent> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(stockMinutePriceCollectionRequestedEventConsumerFactory());
        factory.setBatchListener(true);
        factory.setConcurrency(3);
        return factory;
    }
}
