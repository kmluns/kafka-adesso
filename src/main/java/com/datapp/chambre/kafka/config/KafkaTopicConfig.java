package com.datapp.chambre.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

/**
 * Created by kmluns on 21.12.2020
 */
@Configuration
public class KafkaTopicConfig {


    @Value(value = "${kafka.bootstrapAddress}")
    private String bootstrapAddress;

    @Value(value = "${live.topic.name}")
    private String liveTopicName;

    @Value(value = "${notlive.topic.name}")
    private String notliveTopicName;

    @Bean
    public NewTopic liveTopic () {
        return TopicBuilder.name( liveTopicName )
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic notliveTopic () {
        return TopicBuilder.name( notliveTopicName )
                .partitions(1)
                .replicas(1)
                .build();
    }




}
