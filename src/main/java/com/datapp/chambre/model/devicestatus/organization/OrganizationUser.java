package com.datapp.chambre.model.devicestatus.organization;

import com.datapp.chambre.authorization.model.Account;
import com.datapp.chambre.model.devicestatus.notification.NotificationInformation;
import com.datapp.chambre.model.devicestatus.organization.role.OrganizationRole;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;

/**
 * Created by kmluns on 25.06.2021
 */
@Getter
@Setter
public class OrganizationUser {

	@DBRef
	@JsonBackReference
	private Account account;

	private OrganizationRole role;

	private NotificationInformation notification;

	public OrganizationUser(){
		this.notification = new NotificationInformation();
	}

	public OrganizationUser(Account account, OrganizationRole role){
		this();
		this.account = account;
		this.role = role;
	}

}
