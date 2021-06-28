package com.datapp.chambre.model.devicestatus.device;

import com.datapp.chambre.model.devicestatus.notification.NotificationType;
import com.datapp.chambre.model.devicestatus.organization.OrganizationUser;
import com.datapp.chambre.model.devicestatus.notification.NotificationAndPaidInformation;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.HashMap;

/**
 * Created by kmluns on 25.06.2021
 */
@Getter
@Setter
public class DeviceUser {

	@DBRef
	@JsonBackReference
	private OrganizationUser organizationUser;

	private HashMap<NotificationType, NotificationAndPaidInformation> notificationInformation;

	public DeviceUser(OrganizationUser organizationUser){
		this.notificationInformation = new HashMap<>();
		this.organizationUser = organizationUser;
	}

}
