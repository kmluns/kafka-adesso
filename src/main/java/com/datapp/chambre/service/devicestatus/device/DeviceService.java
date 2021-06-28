package com.datapp.chambre.service.devicestatus.device;

import com.datapp.chambre.authorization.model.Account;
import com.datapp.chambre.dto.request.devicestatus.device.DeviceAddOrDeleteDTO;
import com.datapp.chambre.dto.response.GenericResponse;
import com.datapp.chambre.model.devicestatus.device.Device;

import java.util.List;

/**
 * Created by kmluns on 25.06.2021
 */
public interface DeviceService {

	GenericResponse<Device> addDevice( Account currentAccount, DeviceAddOrDeleteDTO deviceAddOrDeleteDTO);
	Device getDevice(String deviceId);
	Device getDevice(String ipAddress, int port);
	boolean existDevice(String ipAddress, int port);

	List<Device> getDeviceList ();
}
