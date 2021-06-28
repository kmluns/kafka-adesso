package com.datapp.chambre.schedule.device;

import com.datapp.chambre.constant.KafkaConstants;
import com.datapp.chambre.model.devicestatus.device.Device;
import com.datapp.chambre.model.devicestatus.device.DeviceHeartBeat;
import com.datapp.chambre.service.devicestatus.device.DeviceService;
import com.datapp.chambre.utils.devicestatus.DeviceNetworkUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by kmluns on 27.06.2021
 */
@Component
public class DeviceHearthBeatScheduler {

	private DeviceService deviceService;

	private KafkaTemplate<String, DeviceHeartBeat> kafkaTemplate;

	@Autowired
	public void setDeviceService(DeviceService deviceService){ this.deviceService = deviceService;}

	@Autowired
	public void setKafkaTemplate(KafkaTemplate kafkaTemplate){ this.kafkaTemplate = kafkaTemplate;}

	@Scheduled (fixedRate = 1000)
	public void deviceHearthBeat() throws ExecutionException, InterruptedException {

		final ExecutorService es = Executors.newFixedThreadPool(20);
		final List<Future<DeviceHeartBeat>> futures = new ArrayList<>();

		List<Device> deviceList = deviceService.getDeviceList();
		for(Device device : deviceList) {
			futures.add(DeviceNetworkUtil.deviceIsOpen( es, device));
		}

		es.shutdown();

		for (final Future<DeviceHeartBeat> f : futures) {
			DeviceHeartBeat deviceHeartBeat = f.get();

			if (deviceHeartBeat.isLive()) {
				kafkaTemplate.send( KafkaConstants.KAFKA_LIVE_TOPIC, deviceHeartBeat).get();
			} else {
				kafkaTemplate.send( KafkaConstants.KAFKA_NOT_LIVE_TOPIC, deviceHeartBeat).get();
			}
		}

	}

}
