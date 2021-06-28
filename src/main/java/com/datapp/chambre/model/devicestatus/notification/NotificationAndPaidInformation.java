package com.datapp.chambre.model.devicestatus.notification;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by kmluns on 25.06.2021
 */
@Getter
@Setter
public class NotificationAndPaidInformation {

	private boolean open = false;
	private boolean paid = false;

	public NotificationAndPaidInformation(){}

	public NotificationAndPaidInformation(boolean paid){
		this.paid = paid;
	}

	public NotificationAndPaidInformation(boolean paid, boolean open){
		this.paid = paid;
		this.open = open;
	}

}
