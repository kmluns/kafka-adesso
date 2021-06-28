package com.datapp.chambre.model.devicestatus.organization;

import com.datapp.chambre.model.devicestatus.device.Device;
import com.datapp.chambre.model.devicestatus.device.DeviceUser;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kmluns on 25.06.2021
 */
@Getter
@Setter
public class OrganizationDevice {

	@DBRef
	private Device device;

	private List<DeviceUser> observerUsers;

	public OrganizationDevice(Device device, OrganizationUser organizationUser ) {
		this.device = device;
		this.observerUsers = new ArrayList<>();
		this.observerUsers.add( new DeviceUser(organizationUser) );
	}
}
