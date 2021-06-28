package com.datapp.chambre.kafka.service.producer;

import com.datapp.chambre.model.devicestatus.device.DeviceHeartBeat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

/**
 * Created by kmluns on 21.12.2020
 */
@Service
public class KafkaProducerService {
    private static final Logger logger =
            LoggerFactory.getLogger(KafkaProducerService.class);

    //1. Kick Vote topic

    @Value(value = "${live.topic.name}")
    private String liveTopicName;

    @Autowired
    private KafkaTemplate<String, DeviceHeartBeat> kickKafkaTemplate;

    //2. Message Topic

    @Value(value = "${notlive.topic.name}")
    private String notliveTopicName;

    @Autowired
    private KafkaTemplate<String, DeviceHeartBeat> messageKafkaTemplate;

    public void sendLiveInformation(DeviceHeartBeat beat) {
        ListenableFuture<SendResult<String, DeviceHeartBeat>> future
                = this.kickKafkaTemplate.send( liveTopicName, beat);

        future.addCallback(new ListenableFutureCallback<SendResult<String, DeviceHeartBeat>>() {
            @Override
            public void onSuccess(SendResult<String, DeviceHeartBeat> result) {
                logger.info("Live topic is started: " + beat
                        + " with offset: " + result.getRecordMetadata().offset());
            }

            @Override
            public void onFailure(Throwable ex) {
                logger.error("Unable to send beat : " + beat, ex);
            }
        });
    }

    public void sendNotLiveInformation(DeviceHeartBeat beat) {
        ListenableFuture<SendResult<String, DeviceHeartBeat>> future
                = this.messageKafkaTemplate.send( notliveTopicName, beat);

        future.addCallback(new ListenableFutureCallback<SendResult<String, DeviceHeartBeat>>() {
            @Override
            public void onSuccess(SendResult<String, DeviceHeartBeat> result) {
                logger.info("Not Live topic is sent: "
                        + beat + " with offset: " + result.getRecordMetadata().offset());
            }

            @Override
            public void onFailure(Throwable ex) {
                logger.error("Message is sent : " + beat, ex);
            }
        });
    }
}
