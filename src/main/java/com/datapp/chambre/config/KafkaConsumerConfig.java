//package com.datapp.chambre.config;
//
//import com.datapp.chambre.constant.KafkaConstants;
//import com.datapp.chambre.model.devicestatus.device.DeviceHeartBeat;
//import org.apache.kafka.clients.consumer.ConsumerConfig;
//import org.apache.kafka.common.serialization.StringDeserializer;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
//import org.springframework.kafka.core.ConsumerFactory;
//import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
//import org.springframework.kafka.support.serializer.JsonDeserializer;
//
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * Created by kmluns on 3.10.2020
// */
////@Configuration
//public class KafkaConsumerConfig {
//    @Bean
//    ConcurrentKafkaListenerContainerFactory<String, DeviceHeartBeat> kafkaListenerContainerFactory() {
//        ConcurrentKafkaListenerContainerFactory<String, DeviceHeartBeat> factory = new ConcurrentKafkaListenerContainerFactory<>();
//        factory.setConsumerFactory(consumerFactory());
//        return factory;
//    }
//
//    @Bean
//    public ConsumerFactory<String, DeviceHeartBeat> consumerFactory() {
//        return new DefaultKafkaConsumerFactory<>(consumerConfigurations(), new StringDeserializer(), new JsonDeserializer<>(DeviceHeartBeat.class));
//    }
//
//    @Bean
//    public Map<String, Object> consumerConfigurations() {
//        Map<String, Object> configurations = new HashMap<>();
//        configurations.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaConstants.KAFKA_BROKER);
//        configurations.put(ConsumerConfig.GROUP_ID_CONFIG, KafkaConstants.GROUP_ID);
//        configurations.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        configurations.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
//        configurations.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
//        return configurations;
//    }
//}
