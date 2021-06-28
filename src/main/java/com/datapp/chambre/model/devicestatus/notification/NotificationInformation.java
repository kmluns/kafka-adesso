package com.datapp.chambre.model.devicestatus.notification;

import lombok.Getter;

import java.util.HashMap;

/**
 * Created by kmluns on 25.06.2021
 */
@Getter
public class NotificationInformation {
	private HashMap<NotificationType, NotificationAndPaidInformation> informationMap = new HashMap<>();

	public boolean open(NotificationType notificationType) {
		NotificationAndPaidInformation information = informationMap.get( notificationType );
		if(information != null) {
			information.setOpen(true);
			return true;
		}
		return false;
	}

	public void payed(NotificationType notificationType) {
		informationMap.put( notificationType, new NotificationAndPaidInformation(true) );
	}

	public boolean isNotification(NotificationType notificationType) {
		NotificationAndPaidInformation information = informationMap.get( notificationType );
		if(information != null) {
			return information.isOpen();
		}
		return false;
	}

	public boolean canNotification(NotificationType notificationType) {
		NotificationAndPaidInformation information = informationMap.get( notificationType );
		if(information != null) {
			return information.isPaid() && information.isOpen();
		}
		return false;
	}

}
