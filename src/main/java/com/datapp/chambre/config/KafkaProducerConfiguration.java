//package com.datapp.chambre.config;
//
//import com.datapp.chambre.constant.KafkaConstants;
//import com.datapp.chambre.model.devicestatus.device.DeviceHeartBeat;
//import org.apache.kafka.clients.producer.ProducerConfig;
//import org.apache.kafka.common.serialization.StringSerializer;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.core.DefaultKafkaProducerFactory;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.kafka.core.ProducerFactory;
//import org.springframework.kafka.support.serializer.JsonSerializer;
//
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * Created by kmluns on 3.10.2020
// */
////@Configuration
//public class KafkaProducerConfiguration {
//    @Bean
//    public ProducerFactory<String, DeviceHeartBeat> producerFactory() {
//        return new DefaultKafkaProducerFactory<>(producerConfigurations());
//    }
//
//    @Bean
//    public Map<String, Object> producerConfigurations() {
//        Map<String, Object> configurations = new HashMap<>();
//        configurations.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaConstants.KAFKA_BROKER);
//        configurations.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//        configurations.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
//        return configurations;
//    }
//
//    @Bean
//    public KafkaTemplate<String, DeviceHeartBeat> kafkaTemplate() {
//        return new KafkaTemplate<>(producerFactory());
//    }
//}
