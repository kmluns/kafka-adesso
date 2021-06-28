package com.datapp.chambre.kafka.service.consumer;

import com.datapp.chambre.model.devicestatus.device.DeviceHeartBeat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * Created by kmluns on 21.12.2020
 */
@Service
public class KafkaConsumerService {
    private final Logger logger
            = LoggerFactory.getLogger(KafkaConsumerService.class);

    @KafkaListener(topics = "${live.topic.name}",
            groupId = "${live.topic.group.id}")
    public void consumeLiveTopic(DeviceHeartBeat beat) {
        logger.info(String.format("Live beat recieved :", beat));
    }

    @KafkaListener(topics = "${notlive.topic.name}",
            groupId = "${notlive.topic.group.id}",
            containerFactory = "userKafkaListenerContainerFactory")
    public void consumeNotLiveTopic( DeviceHeartBeat beat) {
        logger.info(String.format("Not live beat received : ", beat));
    }
}
