package com.datapp.chambre.model.devicestatus.organization;

import com.datapp.chambre.model.devicestatus.notification.NotificationInformation;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;

/**
 * Created by kmluns on 25.06.2021
 */
@Getter
@Setter
public class ObservableOrganization {

	@DBRef
	@JsonBackReference
	private Organization organization;

	private NotificationInformation notification;

	public ObservableOrganization(Organization organization) {
		this.notification = new NotificationInformation();
		this.organization = organization;
	}
}
