package com.gyeryongbrother.pickandtest.stock.messaging.publisher.kafka.config;

import java.util.HashMap;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

@Configuration
public class KafkaTopicConfig {

    private final String bootstrapServers;

    public KafkaTopicConfig(
            @Value("${kafka.bootstrap-servers}") String bootstrapServers
    ) {
        this.bootstrapServers = bootstrapServers;
    }

    @Bean
    public KafkaAdmin kafkaAdmin() {
        HashMap<String, Object> props = new HashMap<>();
        props.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        return new KafkaAdmin(props);
    }

    @Bean
    public NewTopic stockPriceCollectionRequestedEventTopic() {
        return TopicBuilder.name("stock-price-collection-requested-event")
                .partitions(3)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic stockMinutePriceCollectionRequestedEventTopic() {
        return TopicBuilder.name("stock-minute-price-collection-requested-event")
                .partitions(3)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic stockCreatedEventTopic() {
        return TopicBuilder.name("stock-created-event")
                .partitions(3)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic stockPriceCollectionRequestedEventDltTopic() {
        return TopicBuilder.name("stock-price-collection-requested-event.DLT")
                .partitions(3)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic stockMinutePriceCollectionRequestedEventDltTopic() {
        return TopicBuilder.name("stock-minute-price-collection-requested-event.DLT")
                .partitions(3)
                .replicas(1)
                .build();
    }
}
