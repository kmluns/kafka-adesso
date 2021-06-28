package com.datapp.chambre.service.devicestatus.organization.impl;

import com.datapp.chambre.authorization.model.Account;
import com.datapp.chambre.dto.request.devicestatus.organization.OrganizationCreateEditDTO;
import com.datapp.chambre.dto.response.GenericResponse;
import com.datapp.chambre.exception.runtime.ParseException;
import com.datapp.chambre.exception.runtime.UserNotFoundException;
import com.datapp.chambre.model.devicestatus.device.Device;
import com.datapp.chambre.model.devicestatus.organization.Organization;
import com.datapp.chambre.model.devicestatus.organization.OrganizationDevice;
import com.datapp.chambre.model.devicestatus.organization.OrganizationUser;
import com.datapp.chambre.model.devicestatus.organization.authorize.OrganizationAuthorizationOperation;
import com.datapp.chambre.model.devicestatus.organization.role.OrganizationRole;
import com.datapp.chambre.repository.devicestatus.organization.OrganizationRepository;
import com.datapp.chambre.service.GenericResponseService;
import com.datapp.chambre.service.common.user.AccountService;
import com.datapp.chambre.service.devicestatus.organization.OrganizationService;
import com.datapp.chambre.utils.devicestatus.organization.authorization.OrganizationAuthorizeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by kmluns on 25.06.2021
 */
@Service
public class OrganizationServiceImpl implements OrganizationService {

	OrganizationRepository organizationRepository;

	GenericResponseService genericResponseService;

	AccountService accountService;

	@Autowired
	public void setOrganizationRepository(OrganizationRepository organizationRepository){ this.organizationRepository = organizationRepository;}

	@Autowired
	public void setGenericResponseService(GenericResponseService genericResponseService){ this.genericResponseService = genericResponseService;}

	@Autowired
	public void setAccountService( AccountService accountService){ this.accountService = accountService;}

	@Override
	public GenericResponse<Organization> addOrganization ( Account currentAccount, OrganizationCreateEditDTO organizationCreateEditDTO )
	{
		if(existOrganization(currentAccount, organizationCreateEditDTO.getName())) {
			throw new ParseException(); // TODO : ORGANIZATION EXIST ERROR
		}

		Organization organization = new Organization(organizationCreateEditDTO.getName(),currentAccount);
		organization = organizationRepository.save( organization );

		accountService.addOrganization(currentAccount, organization, OrganizationRole.FOUNDER );

		return genericResponseService.createResponseNoError( organization );
	}

	@Override
	public Organization getOrganization ( String organizationID )
	{
		Optional<Organization> optionalOrganization = organizationRepository.findById( organizationID );
		return optionalOrganization.orElseThrow( UserNotFoundException::new);
	}

	@Override
	public void addDevice ( Account currentAccount, Organization organization, Device device ) {
		if ( OrganizationAuthorizeUtil.authorize( OrganizationAuthorizationOperation.ADD_MODEM, organization, currentAccount ) ) {
			for(OrganizationUser organizationUser : organization.getUserList()){
				if(organizationUser.getAccount().getId().equals( currentAccount.getId())) {
					organization.getDeviceList().add( new OrganizationDevice(device, organizationUser) );
					organizationRepository.save( organization );
					break;
				}
			}
		}
	}

	private boolean existOrganization(Account account, String name) {
		return account.getManageableOrganizations().stream().anyMatch(
				organization -> organization.getOwner().getId().equals(account.getId())
						&& organization.getName().equals( name )
		);
	}
}
