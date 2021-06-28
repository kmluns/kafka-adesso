package com.datapp.chambre.exception.runtime;

import com.datapp.chambre.constant.ErrorMessageConstant;
import com.datapp.chambre.utils.app_static.StaticUtils;

/**
 * created by kmluns
 **/
public class UserNotFoundException extends ApplicationRuntimeException {
	public UserNotFoundException () {
		super( StaticUtils.getErrorMessageService().getErrorMessageText( ErrorMessageConstant.USER_NOT_FOUND ), ErrorMessageConstant.USER_NOT_FOUND );
	}
}
