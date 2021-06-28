package com.datapp.chambre.exception.runtime.devicestatus;

import com.datapp.chambre.constant.ErrorMessageConstant;
import com.datapp.chambre.exception.runtime.ApplicationRuntimeException;
import com.datapp.chambre.utils.app_static.StaticUtils;

/**
 * Created by kmluns on 25.06.2021
 */
public class OrganizationNotFoundException extends ApplicationRuntimeException {
	public OrganizationNotFoundException() {
		super( StaticUtils.getErrorMessageService().getErrorMessageText( ErrorMessageConstant.ORGANIZATION_NOT_FOUND ), ErrorMessageConstant.ORGANIZATION_NOT_FOUND );
	}
}
