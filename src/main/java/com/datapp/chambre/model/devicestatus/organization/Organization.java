package com.datapp.chambre.model.devicestatus.organization;

import com.datapp.chambre.authorization.model.Account;
import com.datapp.chambre.model.devicestatus.organization.role.OrganizationRole;
import com.datapp.chambre.model.utils.Base;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kmluns on 25.06.2021
 */
@Document (collection = "organization")
@Accessors (chain = true)
@Getter
@Setter
public class Organization extends Base {

	private String name;

	@DBRef
	@JsonBackReference
	private Account owner;

	private List<OrganizationDevice> deviceList;

	private List<OrganizationUser> userList;

	// TODO : @Value ekle
	private String defaultPassword;

	public Organization() {
		this.deviceList = new ArrayList<>();
		this.userList = new ArrayList<>();
	}

	public Organization(String name, Account owner) {
		this();
		this.name = name;
		this.owner = owner;
		userList.add( new OrganizationUser(owner, OrganizationRole.FOUNDER) );
	}

}
