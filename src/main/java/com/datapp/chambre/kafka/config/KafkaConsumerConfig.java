package com.datapp.chambre.kafka.config;

import com.datapp.chambre.model.devicestatus.device.DeviceHeartBeat;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kmluns on 21.12.2020
 */
@Configuration
public class KafkaConsumerConfig
{
    @Value(value = "${kafka.bootstrapAddress}")
    private String bootstrapAddress;

    @Value(value = "${live.topic.group.id}")
    private String liveGroupId;

    @Value(value = "${notlive.topic.group.id}")
    private String notLiveGroupId;

    // 1. Consume string data from Kafka

    @Bean
    public ConsumerFactory<String, DeviceHeartBeat> consumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, liveGroupId );
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class);
        props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
//        return new DefaultKafkaConsumerFactory<>(props);

        return new DefaultKafkaConsumerFactory<>(props,
                new StringDeserializer(),
                new JsonDeserializer<>(DeviceHeartBeat.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, DeviceHeartBeat>
    kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, DeviceHeartBeat> factory
                = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }




    // 2. Consume user objects from Kafka

    public ConsumerFactory<String, DeviceHeartBeat> userConsumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, notLiveGroupId );
        props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
        return new DefaultKafkaConsumerFactory<>(props,
                new StringDeserializer(),
                new JsonDeserializer<>(DeviceHeartBeat.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, DeviceHeartBeat>
    userKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, DeviceHeartBeat> factory
                = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(userConsumerFactory());
        return factory;
    }
}
