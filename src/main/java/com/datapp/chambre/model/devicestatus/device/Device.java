package com.datapp.chambre.model.devicestatus.device;

import com.datapp.chambre.model.devicestatus.organization.Organization;
import com.datapp.chambre.model.utils.Base;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by kmluns on 25.06.2021
 */
@Document( collection = "device")
@Accessors(chain = true)
@Getter
@Setter
public class Device extends Base {

	private String name;

	private String ipAddress;

	private int port;

	@DBRef
	@JsonBackReference
	private Set<Organization> organizationList = new HashSet<>();

	public void addOrganization(Organization organization){
		organizationList.add( organization );
	}

}
