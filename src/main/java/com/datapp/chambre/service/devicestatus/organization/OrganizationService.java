package com.datapp.chambre.service.devicestatus.organization;

import com.datapp.chambre.authorization.model.Account;
import com.datapp.chambre.dto.request.devicestatus.organization.OrganizationCreateEditDTO;
import com.datapp.chambre.dto.response.GenericResponse;
import com.datapp.chambre.model.devicestatus.device.Device;
import com.datapp.chambre.model.devicestatus.organization.Organization;

/**
 * Created by kmluns on 25.06.2021
 */
public interface OrganizationService {
	GenericResponse<Organization> addOrganization( Account currentAccount, OrganizationCreateEditDTO organizationCreateEditDTO );
	Organization getOrganization ( String organizationID );

	void addDevice ( Account currentAccount, Organization organization, Device device );
}
