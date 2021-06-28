package com.datapp.chambre.repository.devicestatus.device;

import com.datapp.chambre.model.devicestatus.device.Device;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by kmluns on 25.06.2021
 */
@Repository
public interface DeviceRepository extends MongoRepository<Device, String> {

	Optional<Device> findByIpAddressAndPort(String ipAdress, int port);

}
