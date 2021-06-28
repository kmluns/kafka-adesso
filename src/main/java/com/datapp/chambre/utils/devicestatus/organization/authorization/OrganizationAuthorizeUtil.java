package com.datapp.chambre.utils.devicestatus.organization.authorization;

import com.datapp.chambre.authorization.model.Account;
import com.datapp.chambre.authorization.model.Role;
import com.datapp.chambre.model.devicestatus.organization.Organization;
import com.datapp.chambre.model.devicestatus.organization.authorize.OrganizationAuthorizationOperation;
import com.datapp.chambre.model.devicestatus.organization.role.OrganizationRole;

/**
 * Created by kmluns on 25.06.2021
 */
public class OrganizationAuthorizeUtil {
	private OrganizationAuthorizeUtil () { }

	public static boolean authorize ( OrganizationAuthorizationOperation operation, Organization organization,
			Account currentAccount ) {
		if ( organization.getOwner().equals( currentAccount ) && currentAccount.hasRole( Role.ADMIN ) ) {
			return true;
		}

		switch ( operation ) {
			case ADD_MODEM:
			case EDIT_MODEM:
			case REMOVE_MODEM:
				return authorizeForFieldOfficer( organization, currentAccount );
			default:
				break;
		}
		return false;
	}

	private static boolean authorizeForFieldOfficer ( Organization organization, Account currentAccount ) {
		return organization.getUserList().stream().anyMatch(
				organizationUser -> currentAccount.getId().equals( organizationUser.getAccount().getId() ) && organizationUser.getRole()
						.equals( OrganizationRole.FIELD_OFFICER ) );
	}

}
