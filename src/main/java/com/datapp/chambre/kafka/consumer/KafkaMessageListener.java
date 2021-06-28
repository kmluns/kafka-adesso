package com.datapp.chambre.kafka.consumer;

import com.datapp.chambre.constant.KafkaConstants;
import com.datapp.chambre.model.devicestatus.device.DeviceHeartBeat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by kmluns on 3.10.2020
 */
@Component
public class KafkaMessageListener {
    @Autowired
    SimpMessagingTemplate template;

    @KafkaListener(
            topics = KafkaConstants.KAFKA_LIVE_TOPIC,
            groupId = KafkaConstants.GROUP_ID
    )

    public void listen( DeviceHeartBeat beat) {
        System.out.println("sending via kafka listener..");
        template.convertAndSend("/topic/group", beat);
    }
}
